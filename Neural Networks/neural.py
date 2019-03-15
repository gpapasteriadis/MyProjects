from random import seed
import decimal
import random
from math import exp
from math import sqrt
import matplotlib.pyplot as plt

examplePoints = []
examplePoints2 = []
valuesS1 = 6000
valuesS2 = 500
expected = []
outputs = 3
inputs = 2
H1 = 8
H2 = 5
epoch = 300
learningRate = 0.2
function = 0 # ~~(0)sigmoid, (1)tahn~~

M=5
centroids = []
tolerance = 0.0001  #When the difference between the old and new centroids is less than the tolerance value, we stop the iterations
max_iterations = 100 	#specifies the maximum number of times the algorithm can iterate trying to optimize the centroid values


def Euclidean_distance(one,two):
	squared_distance = 0
	for i in range(len(one)):
		squared_distance += (one[0][i]-two[0][i])**2
	ed = sqrt(squared_distance)
	return ed
##################################
def Kmeans():
	TrainingFile = open("TrainingFileS2.txt","r")
	for i in range(M):
		tempPoint = []
		lines = open("TrainingFileS2.txt").read().splitlines()
		line = random.choice(lines)
		myline = line.split(",")
		x1 = float(myline[0][1:])
		x2 = float(myline[1][:len(myline[1])-2])
		tempPoint.append((x1,x2))
		centroids.append(tempPoint) ##thelw metatroph apo string se float,dld na kanw append pleiada alla float

    #main		
	for i in range (max_iterations):
		classes = {}
		for i in range (M):
			classes[i] = []
		##kathgoriopoihsh twn paradeigamtwn
		for line in TrainingFile:
			tempPoint = []
			myline=line.split(",")
			x1 = float(myline[0][1:])
			x2 = float(myline[1][:len(myline[1])-2])
			tempPoint.append((x1,x2))
			distances = [Euclidean_distance(tempPoint,centroids[i]) for i in range(len(centroids))]
			classification = distances.index(min(distances))
			classes[classification].append(tempPoint)
		TrainingFile.seek(0)
		sumx = 0
		sumy = 0
		previous = centroids[:]
		
		#Ananewsh twn kentrwn,upologismos mesou orou
		for classification in classes:
			tempPoint = []
			for i in range(len(classes[classification])):
				sumx += classes[classification][i][0][0]
				sumy += classes[classification][i][0][1] 
			x = sumx/len(classes[classification])
			y = sumy/len(classes[classification])
			tempPoint.append((x,y))
			centroids[classification] = tempPoint
			sumx = 0
			sumy = 0
		print(centroids[0][0])
		print(previous[0][0])
		#diaspora
		scattering = {}
		tempPoint = []
		for classification in classes:
			scattering[classification] = []
			for i in range(len(classes[classification])):
				sumx += (classes[classification][i][0][0]-centroids[classification][0][0])**2
				sumy += (classes[classification][i][0][1] -centroids[classification][0][1])**2
			tempPoint.append((sumx,sumy))
			scattering[classification].append(tempPoint)
			sumx = 0
			sumy = 0

		#elegxoume periptwsh gia termatismo
		isOptimal = True
		for i in range(len(centroids)):
			old_centroid = previous[i]
			current = centroids[i]
			sumx += ((current[0][0] - old_centroid[0][0]))
			sumy += ((current[0][1] - old_centroid[0][1]))
			if (abs(sumx)>tolerance and abs(sumy)>tolerance):
				isOptimal = False
				
		if isOptimal:
			plt.plot(centroids[0][0],"r*")
			plt.plot(centroids[1][0],"g*")
			plt.plot(centroids[2][0],"b*")
			plt.plot(centroids[3][0],"k*")
			plt.plot(centroids[4][0],"y*")
			for classification in classes:
				for i in range(len(classes[classification])):
					if(classification==0):
						plt.plot(classes[classification][i][0][0],classes[classification][i][0][1],"r+")
					elif(classification==1):
						plt.plot(classes[classification][i][0][0],classes[classification][i][0][1],"g+")
					elif(classification==2):
						plt.plot(classes[classification][i][0][0],classes[classification][i][0][1],"b+")
					elif(classification==3):
						plt.plot(classes[classification][i][0][0],classes[classification][i][0][1],"k+")
					else:
						plt.plot(classes[classification][i][0][0],classes[classification][i][0][1],"y+")
			plt.show();
			break
def S1():
	TrainingFile = open("TrainingFile.txt","w") 
	TestFile = open("TestFile.txt","w") 
	global n
	for i in range(0,valuesS1):
		if(i<(valuesS1/2)):
			examplePoints.append((random.uniform(0.0,2.0),random.uniform(0.0,2.0))[:5])
			if(i<1500):
				TrainingFile.write(str(examplePoints[i])+"\n")
			else:
				TestFile.write(str(examplePoints[i])+"\n")
		else:
			examplePoints.append((random.uniform(-2.0,0.0),random.uniform(-2.0,0.0))[:5])
			if(i<4500):
				TrainingFile.write(str(examplePoints[i])+"\n")
			else:
				TestFile.write(str(examplePoints[i])+"\n")

	TrainingFile.close()
	TestFile.close()

def S2():
	TrainingFile2 = open("TrainingFileS2.txt","w")
	for i in range(0,valuesS2):
		if(i<100):
			examplePoints2.append((random.uniform(-0.3,0.3),random.uniform(-0.3,0.3))[:5])
			TrainingFile2.write(str(examplePoints2[i])+"\n")
		elif(i<200):
			examplePoints2.append((random.uniform(-1.1,-0.5),random.uniform(0.5,1.1))[:5])
			TrainingFile2.write(str(examplePoints2[i])+"\n")
		elif(i<300):
			examplePoints2.append((random.uniform(-1.1,-0.5),random.uniform(-1.1,-0.5))[:5])
			TrainingFile2.write(str(examplePoints2[i])+"\n")
		elif(i<400):
			examplePoints2.append((random.uniform(0.5,1.1),random.uniform(-1.1,-0.5))[:5])
			TrainingFile2.write(str(examplePoints2[i])+"\n")
		elif(i<500):
			examplePoints2.append((random.uniform(0.5,1.1),random.uniform(0.5,1.1))[:5])
			TrainingFile2.write(str(examplePoints2[i])+"\n")
	
	TrainingFile2.close()

def initialize_network(inputs,h1,h2,outputs):
	network = list()
	H1 = [{'weights':[random.uniform(-1.0,1.0) for i in range(inputs + 1)]} for i in range(h1)]
	network.append(H1)
	H2 = [{'weights':[random.uniform(-1.0,1.0)  for i in range(h1 + 1)]} for i in range(h2)]
	network.append(H2)
	output_layer = [{'weights':[random.uniform(-1.0,1.0)  for i in range(h2 + 1)]} for i in range(outputs)]
	network.append(output_layer)
	for x in network:
		print(x)
		print()
	return network


# Calculate neuron activation for an input
def activate(weights, inputs):
	activation = weights[-1]
	for i in range(len(weights)-1):
		activation += weights[i] * inputs[i]
	return activation

# Transfer neuron activation NA KANOUME LOGISTIKI
def transfer(activation):
	#print (float(str(activation)[:6]))
	if (function==0):
		return 1.0 / (1.0 + exp(-float(str(activation)[:5])))
	if (function==1):
		return (exp(2*(float(str(activation)[:5])))-1) / (exp(2*(float(str(activation)[:5])))+1)
# Forward propagate input to a network output
def forward_propagate(network, row):
	inputs = row
	for layer in network:
		new_inputs = []
		for neuron in layer:
			activation = activate(neuron['weights'], inputs)
			neuron['output'] = transfer(activation)
			new_inputs.append(neuron['output'])
		inputs = new_inputs
	return inputs

# test forward propagation




# Calculate the derivative of an neuron output
def transfer_derivative(output):
	return output * (1.0 - output)

# Backpropagate error and store in neurons
def backward_propagate_error(network, expected):
	for i in reversed(range(len(network))):
		layer = network[i]
		errors = list()
		if i != len(network)-1:
			for j in range(len(layer)):
				error = 0.0
				for neuron in network[i + 1]:
					error += (neuron['weights'][j] * neuron['delta'])
				errors.append(error)
		else:
			for j in range(len(layer)):
				neuron = layer[j]
				errors.append(expected[j] - neuron['output'])
		for j in range(len(layer)):
			neuron = layer[j]
			neuron['delta'] = errors[j] * transfer_derivative(neuron['output'])

# Update network weights with error
def update_weights(network, row, l_rate):
	for i in range(len(network)):
		inputs = row[:-1]
		if i != 0:
			inputs = [neuron['output'] for neuron in network[i - 1]]
		for neuron in network[i]:
			for j in range(len(inputs)):
				neuron['weights'][j] += l_rate * neuron['delta'] * inputs[j]
			neuron['weights'][-1] += l_rate * neuron['delta']

# Train a network for a fixed number of epochs
def trainNetwork(network, TrainingFile, l_rate, epoch, n_outputs):
	for currentEpoch in range(epoch):
		sum_error = 0
		count1 = 0
		for line in TrainingFile:
			myline=line.split(",")
			x1 = float(myline[0][1:])
			x2 = float(myline[1][:len(myline[1])-2])
			if(((x1-1)**2 + (x2-1)**2) <= 0.16):
				expected=[1,0,0]
			elif(((x1+1)**2 + (x2+1)**2) <= 0.16):
				expected=[1,0,0]
			elif(((x1-1)**2 + (x2-1)**2) > 0.16 and ((x1-1)**2 + (x2-1)**2) < 0.64):
				expected=[0,1,0]
			elif(((x1+1)**2 + (x2+1)**2) > 0.16 and ((x1+1)**2 + (x2+1)**2) < 0.64):
				expected=[0,1,0]
			else:
				expected=[0,0,1]
			row = [x1,x2, None]
			outputs = forward_propagate(network, row)
			sum_error += sum([(expected[i]-outputs[i])**2 for i in range(len(expected))])
			backward_propagate_error(network, expected)
			update_weights(network, row, l_rate)

		TrainingFile.seek(0)
		print('>epoch=%d, lrate=%.3f, error=%.3f' % (currentEpoch, l_rate, sum_error))
	return network

def testNetwork(network,TestFile):
	error = 0
	sum_error = 0
	counter = 0
	for line in TestFile:
		counter+=1
		myline=line.split(",")
		x1 = float(myline[0][1:])
		x2 = float(myline[1][:len(myline[1])-2])
		if(((x1-1)**2 + (x2-1)**2) <= 0.16):
			expected=[1,0,0]
		elif(((x1+1)**2 + (x2+1)**2) <= 0.16):
			expected=[1,0,0]
		elif(((x1-1)**2 + (x2-1)**2) > 0.16 and ((x1-1)**2 + (x2-1)**2) < 0.64):
			expected=[0,1,0]
		elif(((x1+1)**2 + (x2+1)**2) > 0.16 and ((x1+1)**2 + (x2+1)**2) < 0.64):
			expected=[0,1,0]
		else:
			expected=[0,0,1]
		row = [x1,x2, None]
		outputs = forward_propagate(network, row)
		error += sum([(expected[i]-outputs[i])**2 for i in range(len(expected))])
		sum_error += error
		error = 0
	myerror = sum_error/counter
	print('Genikeutiki ikanotita/Sum Error = %.3f ' % (myerror))
	TestFile.seek(0)





			###############~MLP~###################
network = initialize_network(inputs,H1,H2,outputs)
S1()
TrainingFile = open("TrainingFile.txt","r") 
TestFile = open("TestFile.txt","r")
testNetwork(network,TestFile)
network = trainNetwork(network, TrainingFile, learningRate , epoch, outputs)
testNetwork(network,TestFile)
			#############~K-Means~#################
#S2()
#Kmeans()

			##############~LVQ~####################
