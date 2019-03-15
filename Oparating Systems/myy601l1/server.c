/* server.c

   Sample code of 
   Assignment L1: Simple multi-threaded key-value server
   for the course MYY601 Operating Systems, University of Ioannina 

   (c) S. Anastasiadis, G. Kappes 2016

*/


#include <signal.h>
#include <sys/stat.h>
#include "utils.h"
#include "kissdb.h"
#include <sys/time.h>
#include <pthread.h>
#include <stdio.h>

#define MY_PORT                 6767
#define BUF_SIZE                1160
#define KEY_SIZE                 128
#define HASH_SIZE               1024
#define VALUE_SIZE              1024
#define MAX_PENDING_CONNECTIONS   10
#define QUEUE_LENGTH		      10
#define THREAD_NUMBER		      2

// Definition of the operation type.
typedef enum operation {
  PUT,
  GET
} Operation; 

// Definition of the request.
typedef struct request {
  Operation operation;
  char key[KEY_SIZE];  
  char value[VALUE_SIZE];
} Request;

// Definition of the database.
KISSDB *db = NULL;

//*************************************************************

pthread_t id[THREAD_NUMBER];
pthread_cond_t cond = PTHREAD_COND_INITIALIZER;
pthread_cond_t cond1 = PTHREAD_COND_INITIALIZER;
pthread_mutex_t mutex1 = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t mutex2 = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t mutex3 = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t mutex4 = PTHREAD_MUTEX_INITIALIZER;

int completed_requests = 0;
long total_waiting_time = 0;
long total_service_time = 0;

struct queueObject{
	int objectFD;
	struct timeval tv; 
};

int head = 0;
int tail = 0;
int numOfObjects = 0; //current queue length
int itemsInQueue = 0;

struct queueObject queue[QUEUE_LENGTH];





/**
 * @name parse_request - Parses a received message and generates a new request.
 * @param buffer: A pointer to the received message.
 *
 * @return Initialized request on Success. NULL on Error.
 */
Request *parse_request(char *buffer) {
  char *token = NULL;
  Request *req = NULL;
  
  // Check arguments.
  if (!buffer)
    return NULL;
  
  // Prepare the request.
  req = (Request *) malloc(sizeof(Request));
  memset(req->key, 0, KEY_SIZE);
  memset(req->value, 0, VALUE_SIZE);

  // Extract the operation type.
  token = strtok(buffer, ":");    
  if (!strcmp(token, "PUT")) {
    req->operation = PUT;
  } else if (!strcmp(token, "GET")) {
    req->operation = GET;
  } else {
    free(req);
    return NULL;
  }
  
  // Extract the key.
  token = strtok(NULL, ":");
  if (token) {
    strncpy(req->key, token, KEY_SIZE);
  } else {
    free(req);
    return NULL;
  }
  
  // Extract the value.
  token = strtok(NULL, ":");
  if (token) {
    strncpy(req->value, token, VALUE_SIZE);
  } else if (req->operation == PUT) {
    free(req);
    return NULL;
  }
  return req;
}



/*
 * @name process_request - Process a client request.
 * @param socket_fd: The accept descriptor.
 *
 * @return
 */
 
void printQueue(){
	int k;
	/*
    for(k=0;k<QUEUE_LENGTH;k++){
    	printf("%d  ",k);
    }
    printf("\n");*/
     for(k=0;k<QUEUE_LENGTH;k++){
    	printf("%d  ",queue[k].objectFD);
    }
   printf("\n");
   // printf("\n");
}
void process_request(const int socket_fd){

  	char response_str[BUF_SIZE], request_str[BUF_SIZE];
    int numbytes = 0;
    Request *request = NULL;


    // Clean buffers.
    memset(response_str, 0, BUF_SIZE);
    memset(request_str, 0, BUF_SIZE);
    
    // receive message.
    numbytes = read_str_from_socket(socket_fd, request_str, BUF_SIZE);
    
    // parse the request.
    if (numbytes) {
      request = parse_request(request_str);
      if (request) {
        switch (request->operation) {
          case GET:
            // Read the given key from the database.
            if (KISSDB_get(db, request->key, request->value))
              sprintf(response_str, "GET ERROR\n");
            else
              sprintf(response_str, "GET OK: %s\n", request->value);
          	  printf("\n");
            break;
          case PUT:
            // Write the given key/value pair to the database.
            pthread_mutex_lock(&mutex4);
			    if (KISSDB_put(db, request->key, request->value)) 
			      sprintf(response_str, "PUT ERROR\n");
			    else
			      sprintf(response_str, "PUT OK\n");
			  	  printf("\n");
            pthread_mutex_unlock(&mutex4);
            break;
          default:
            // Unsupported operation.
            sprintf(response_str, "UNKOWN OPERATION\n");
        }
        // Reply to the client.
        write_str_to_socket(socket_fd, response_str, strlen(response_str));
        if (request)
          free(request);
        request = NULL;
        return;
      }
    }
    // Send an Error reply to the client.
    sprintf(response_str, "FORMAT ERROR\n");
    write_str_to_socket(socket_fd, response_str, strlen(response_str));
}
int queueIsFull(){

	if((tail == head) & (itemsInQueue == QUEUE_LENGTH)){
		return 1;
	}
	return 0;

}
int  queueIsEmpty(){

	if((tail == head) & (itemsInQueue == 0)){
		return 1;
	}
	return 0;
}

void timeCalculation(long serviceUsecs,long waitingUsecs){
	completed_requests ++;
	total_waiting_time += waitingUsecs;
	total_service_time += serviceUsecs;
}

void *thread(void * arg){
	int currentFD;
	struct timeval waitingTime;
	struct timeval serviceTime;
	long serviceUsecs;
	long waitingUsecs;

	while(1){
		//elegxw an h oura einai adeia kai perimenw!!!

		pthread_mutex_lock(&mutex1);	//lock gia na parei mono ena nyma thn epomenh ethsh
			while(queueIsEmpty()==1){
				pthread_cond_wait(&cond,&mutex1);
			}
	    	currentFD = queue[head].objectFD;
	    	gettimeofday(&waitingTime,NULL);
	    	waitingUsecs = waitingTime.tv_usec - queue[head].tv.tv_usec + 1000000*(waitingTime.tv_sec - queue[head].tv.tv_sec);
	    	queue[head].objectFD = 0;
	    	head = (head+1)%QUEUE_LENGTH;
	    	itemsInQueue -= 1;
	    	pthread_cond_signal(&cond1);
		pthread_mutex_unlock(&mutex1);  //unlock

	    	process_request(currentFD); 
	    	close(currentFD);
	    	gettimeofday(&serviceTime,NULL);
	    	serviceUsecs = (serviceTime.tv_usec + 1000000*(serviceTime.tv_sec)) - waitingTime.tv_usec - (1000000*(waitingTime.tv_sec));

	    pthread_mutex_lock(&mutex2);//kanw lock
		    timeCalculation(serviceUsecs,waitingUsecs);
	    pthread_mutex_unlock(&mutex2); //kanw unlock
    }
    return 0;
}

void handleThreads(){
	int i;
	for(i=0;i<THREAD_NUMBER;i++){
		pthread_detach(id[i]);
	}
	printf("All threads finished!\n");
	KISSDB_close(db);
	exit(0);
}

/*
@printResults - This function prints the
				Completed Requests, Mean Waiting Time
				Mean Service Time.
*/
void printResults(){
	printf("Completed Requests: %d\n", completed_requests);
	printf("Mean Waiting Time: %ld\n", total_waiting_time/completed_requests);
	printf("Mean Service Time: %ld\n", total_service_time/completed_requests);
	handleThreads();
}

void makeThreads(){
	int i;
	for(i=0;i<THREAD_NUMBER;i++){
		pthread_create(&id[i], NULL, thread, NULL);
	}
}


/*
 * @name main - The main routine.
 *
 * @return 0 on success, 1 on error.
 */
int main() {
  struct timeval enterQueueTime;
  int socket_fd,              // listen on this socket for new connections
      new_fd;                 // use this socket to service a new connection
  socklen_t clen;
  struct sockaddr_in server_addr,  // my address information
                     client_addr;  // connector's address information

  // create socket
  if ((socket_fd = socket(AF_INET, SOCK_STREAM, 0)) == -1)
    ERROR("socket()");

  // Ignore the SIGPIPE signal in order to not crash when a
  // client closes the connection unexpectedly.
  signal(SIGPIPE, SIG_IGN);
  
  // create socket adress of server (type, IP-adress and port number)
  bzero(&server_addr, sizeof(server_addr));
  server_addr.sin_family = AF_INET;
  server_addr.sin_addr.s_addr = htonl(INADDR_ANY);    // any local interface
  server_addr.sin_port = htons(MY_PORT);
  
  // bind socket to address
  if (bind(socket_fd, (struct sockaddr *) &server_addr, sizeof(server_addr)) == -1)
    ERROR("bind()");
  
  // start listening to socket for incomming connections
  listen(socket_fd, MAX_PENDING_CONNECTIONS);
  fprintf(stderr, "(Info) main: Listening for new connections on port %d ...\n", MY_PORT);
  clen = sizeof(client_addr);

  // Allocate memory for the database.
  if (!(db = (KISSDB *)malloc(sizeof(KISSDB)))) {
    fprintf(stderr, "(Error) main: Cannot allocate memory for the database.\n");
    return 1;
  }
  
  // Open the database.
  if (KISSDB_open(db, "mydb.db", KISSDB_OPEN_MODE_RWCREAT, HASH_SIZE, KEY_SIZE, VALUE_SIZE)) {
    fprintf(stderr, "(Error) main: Cannot open the database.\n");
    return 1;
  }

  makeThreads();
  
  signal(SIGTSTP,printResults);

  // main loop: wait for new connection/requests
  while (1) {
    // wait for incomming connection
    if ((new_fd = accept(socket_fd, (struct sockaddr *)&client_addr, &clen)) == -1) {
      ERROR("accept()");
    }
    
    // got connection, serve request
    fprintf(stderr, "(Info) main: Got connection from '%s'\n", inet_ntoa(client_addr.sin_addr));
    
    pthread_mutex_lock(&mutex1);
	    while(queueIsFull()==1){		//elegcw an einai gemath h oura kai kanw wait!!
	    	pthread_cond_wait(&cond1,&mutex1);
	    }
	    gettimeofday(&enterQueueTime,NULL);
	    queue[tail].objectFD = new_fd;
	    queue[tail].tv = enterQueueTime;
	    printQueue();
	    tail = (tail+1)%QUEUE_LENGTH;
	    itemsInQueue += 1;
	    pthread_cond_signal(&cond);
    pthread_mutex_unlock(&mutex1);
    
  }  

  // Destroy the database.
  // Close the database.
  KISSDB_close(db);

  // Free memory.
  if (db)
    free(db);
  db = NULL;

  return 0; 
}

