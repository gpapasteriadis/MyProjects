# Gewrgios Papasteriadis AM:2615 username:cse32615
# Athanasios Mpatsilas   AM:2587 username:cse32587
import sys
import json
import copy

array = ['+', '-', '*', '/', '<', '>', '=', '<=', '>=', '<>', ':=', ';', ',', '{', '}', '(', ')', '[', ']', '\*', '*/',
         "",
         "", "", "", 'and_tk', 'declare_tk', 'do_tk', 'else_tk', 'enddeclare_tk', 'exit_tk', 'procedure_tk',
         'function_tk', 'print_tk', 'call_tk',
         'if_tk', 'in_tk', 'inout_tk', 'not_tk', 'select_tk', 'program_tk', 'or_tk', 'return_tk', 'while_tk',
         'default_tk']

white_tk = 0  # Leukos
error_tk = -1  # error_tk
id_tk = 1  # metablhth
digit_tk = 2  # pshfio
plus_tk = 3  # +
minus_tk = 4  # -
multi_tk = 5  # *
division_tk = 6  # /
equal_tk = 7  # =
smaller_tk = 8  # <
different_tk = 9  # <>
smallequal_tk = 10  # <=
greater_tk = 11  # >
greatequal_tk = 12  # >=
assign_tk = 13  # :=
failassign_tk = 14  # :
opcomment_tk = 15  # open comment \*
clcomment_tk = 16  # close comment *\
comma_tk = 17  # ,
scol_tk = 18  # ;
opbrac1_tk = 21  # (
clbrac1_tk = 22  # )
opbrac2_tk = 19  # [
clbrac2_tk = 20  # ]
opbrac3_tk = 45  # {
clbrac3_tk = 46  # }
eof_tk = 23  # eof_tk

and_tk = 25
declare_tk = 26
do_tk = 27
else_tk = 28
enddeclare_tk = 29
exit_tk = 30
procedure_tk = 31
function_tk = 32
print_tk = 33
call_tk = 34
if_tk = 35
in_tk = 36
inout_tk = 37
not_tk = 38
select_tk = 39
program_tk = 40
or_tk = 41
return_tk = 42
while_tk = 43
default_tk = 44

asciiArray = []
assemblyArray = []
funcName = []


class Returntype():
    tokken = 0
    charlex = ""


class Entity(object):
    name = ""
    entType = -1
    nextEntity = 1

    def __init__(self, name1, type1, nextEntity1):
        self.name = name1
        self.entType = type1
        self.nextEntity = nextEntity1

    def make_Entity(name1, type1, nextEntity1):
        entity = Entity(name1, type1, nextEntity1)
        return entity


class Scope():
    entity = []  # lista apo Entities(Pointer)
    nestingLevel = 0  # Bathos fwliasmatos
    enclosingScope = 1  # (Pointer)

    def make_Scope(self):
        scope = Scope()
        return scope


class Argument(object):
    parMode = -1  # 1  Timh  ||  2  Anafora
    argType = 0  # typos metablhths int 0
    nextArg = 1  # (Pointer)

    def __init__(self, parMode1, type1, next1):
        self.parMode = parMode1
        self.argType = type1
        self.nextArg = next1

    def make_Argument(parMode1, type1, next1):
        argument = Argument(parMode1, type1, next1)
        return argument


scopelist = []
entitylist = []
argumentList = []
startQuad = []
offset = 12
entCount = 0
ci = 0
retobj = Returntype()
line = 1
f = open(sys.argv[1], "r+")
#f = open("foo.ci", "r+")
f2 = open("foo.asm", "w+")
asciiLine = 0
counterTemp = 0
newt = []
typeOfCalc = 0
typeRel = 0
strRel = ''
trueList = []
falseList = []
tmp = 0
p = 0
i = 0
v = 0
bi = 0
parCounter = 0
frCounter = 0
offsetlist = []
realoffset = []
framelength = []
ci = 0
lista = []


def addScope():
    global entCount, offset
    if len(scopelist) != 0:
        lista.append(entCount)
        offsetlist.append(offset)
        framelength.append(offset + 4)
    scope = Scope.make_Scope("")
    scopelist.append(scope)
    entCount = 0
    offset = 12


def delScope():
    global entCount, v, offset
    v += 1
    if v == 1:
        lista.append(entCount)
        offsetlist.append(offset)
        framelength.append(offset + 4)
        realoffset.append(offset)
    for i in range(0, lista[len(lista) - 1]):
        entitylist.pop()
    lista.pop()
    scopelist.pop()
    offsetlist.pop()
    offset = offsetlist[len(offsetlist) - 1]
    realoffset.append(offset)


def addEntity(name1, type1, nextentity1):
    global entCount
    entCount += 1
    ent = Entity.make_Entity(name1, type1, nextentity1)
    entitylist.append(ent)
    scopelist[len(scopelist) - 1].entity.append(ent)


def addArgument(parMode1, type1, next1):
    arg = Argument.make_Argument(parMode1, type1, next1)
    argumentList.append(arg)


def searchEntity(name1):
    for scp in scopelist:
        for i in range(0, len(scp.entity) - 1):
            if scp.entity[i].name == name1:
                print("Entity", scp.entity[i].name, "Found!")
                return scp.entity[i]
    print("Entity Not Found!")


def lex():
    c = f.read(1)
    while (c == ' ' or c == "\n" or c == "\t"):
        if c is "\n":
            global line
            line += 1
        c = f.read(1)
    while True:
        b = c
        if c.isalpha():
            retobj.tokken = id_tk
            c = f.read(1)
            while c.isalpha() or c.isnumeric():
                b += c
                c = f.read(1)
            f.seek(f.tell() - 1)
            o = b
            b += "_tk"
            retobj.charlex = b
            if retobj.charlex in array:
                retobj.tokken = array.index(retobj.charlex)
            else:
                retobj.charlex = o
        elif c.isnumeric():
            while c.isnumeric():
                retobj.tokken = digit_tk
                c = f.read(1)
                if c.isnumeric():
                    b += c
                else:
                    if c.isalpha():
                        retobj.tokken = error_tk
                        print("Error: not accepted this type of number. Etc 21a. (LINE): ", line)
                        sys.exit()
            f.seek(f.tell() - 1)
            retobj.charlex = b
            if int(b) > 32767:
                print("Error: not valid int number.Must be until 32767,NOT bigger. (LINE): ", line)
                sys.exit()
        elif c is '\n' or c is ' ' or c is '\t':
            retobj.tokken = white_tk
            if c is "\n":
                retobj.charlex = "\\n"
            elif c is ' ':
                retobj.charlex = " "
            elif c is '\t':
                retobj.charlex = "\\t"

        elif c is '+':
            retobj.tokken = plus_tk
            retobj.charlex = b
        elif c is '-':
            retobj.tokken = minus_tk
            retobj.charlex = b
        elif c is '*':
            retobj.tokken = multi_tk
            retobj.charlex = b
        elif c is '/':
            retobj.tokken = division_tk
            retobj.charlex = b

        elif c is '\\':
            c = f.read(1)
            if c is '*':
                while (1):
                    c = f.read(1)
                    if not c:
                        print("Error: Comments did not end up")
                        retobj.charlex = "Error"
                        retobj.tokken = error_tk
                        return retobj
                        sys.exit()
                    if c is '*':
                        c = f.read(1)
                        if c is '\\':
                            return lex()
                    elif c is '\n':
                        line += 1
            else:
                print("Error: expected '*' after \ ")
                retobj.tokken = error_tk
                retobj.charlex = "Error"
                return retobj
                sys.exit()
        elif c is '[':
            retobj.tokken = opbrac2_tk
            retobj.charlex = b
        elif c is ']':
            retobj.tokken = clbrac2_tk
            retobj.charlex = b
        elif c is '{':
            retobj.tokken = opbrac3_tk
            retobj.charlex = b
        elif c is '}':
            retobj.tokken = clbrac3_tk
            retobj.charlex = b
        elif c is '(':
            retobj.tokken = opbrac1_tk
            retobj.charlex = b
        elif c is ')':
            retobj.tokken = clbrac1_tk
            retobj.charlex = b

        elif c is '<':
            c = f.read(1)
            if c is '=':
                retobj.tokken = smallequal_tk
                retobj.charlex = '<='
            elif c is '>':
                retobj.tokken = different_tk
                retobj.charlex = '<>'
            else:
                retobj.tokken = smaller_tk
                retobj.charlex = b
                f.seek(f.tell() - 1)
        elif c is '>':
            c = f.read(1)
            if c is '=':
                retobj.tokken = greatequal_tk
                retobj.charlex = '>='
            else:
                retobj.tokken = greater_tk
                retobj.charlex = b
                f.seek(f.tell() - 1)
        elif c is '=':
            retobj.tokken = equal_tk
            retobj.charlex = b
        elif c is ',':
            retobj.tokken = comma_tk
            retobj.charlex = b
        elif c is ';':
            retobj.tokken = scol_tk
            retobj.charlex = b
        elif c is ':':
            c = f.read(1)
            if c is '=':
                retobj.tokken = assign_tk
                retobj.charlex = ':='
            else:
                retobj.tokken = failassign_tk
                retobj.charlex = "Failassign"
                f.seek(f.tell() - 1)  # ( : )
        elif c.isprintable() and c is '':
            retobj.tokken = eof_tk
            retobj.charlex = "End Of File!!!(LINE): ", line
            break
        else:
            retobj.tokken = error_tk
            retobj.charlex = "Invalid character!!(LINE): ", line
            sys.exit()
        # print(retobj.charlex, "->", retobj.tokken) #UNCOMMENT TO DEBUG EASILY!!!!
        # print("line =",line)                       #UNCOMMENT TO DEBUG EASILY!!!!
        return (retobj)
        # print(retobj.charlex, "->", retobj.tokken)      #UNCOMMENT TO DEBUG EASILY!!!!
        # print("line =", line)                            #UNCOMMENT TO DEBUG EASILY!!!!


is_program_name = 1
tmpArray = []
addScope()


def syntax(k):
    def program(k):
        global program_name, offset
        if k.tokken == program_tk:
            k = lex()
            program_name = k.charlex
            if k.tokken == id_tk:
                genquad('begin_block', k.charlex, '_', '_')
                k = lex()
                if k.tokken == opbrac1_tk:
                    k = lex()
                    if k.tokken == clbrac1_tk:
                        k = lex()
                        block(k)
                        if len(realoffset) == 0:
                            realoffset.append(offset)
                            offsetlist.append(offset)
                            framelength.append(offset + 4)
                    else:
                        print("Error: Parentheses is open. Right parenthesis was expected.(LINE): ", line)
                else:
                    print("Error: Parentheses did not open. Left parenthesis was expected(LINE): ", line)
            else:
                print("Error: Program name expected.(LINE): ", line)
        else:
            print("Error: The keyword 'program' was expected.(LINE): ", line)
        genquad('end_block', program_name, '_', '_')

    # --------------------------------------------------------------------

    def block(k):
        global is_program_name
        if is_program_name == 1:
            is_program_name = 0
            if k.tokken == opbrac3_tk:
                k = lex()
                y = declarations(k)
                z = subprograms(y)
                w = sequence(z)
                if w.tokken == clbrac3_tk:
                    k = lex()
                else:
                    if w.tokken >= 1 and w.tokken <= 20:
                        print("Error: It's not a valid word.(LINE): ", line)
                    else:
                        print("Error: Parentheses is open. Right parenthesis expected.(LINE): ", line)
            else:
                print("Error: Parentheses did not open. Left parenthesis expected.(LINE): ", line)
            genquad('halt', '_', '_', '_')
        else:
            if k.tokken == opbrac3_tk:
                k = lex()
                y = declarations(k)
                z = subprograms(y)
                w = sequence(z)
                if w.tokken == clbrac3_tk:
                    k = lex()
                else:
                    if w.tokken >= 1 and w.tokken <= 20:
                        print("Error: It's not a valid word.(LINE): ", line)
            else:
                print("Error: Parentheses did not open. Left parenthesis expected.(LINE): ", line)
        return k

    # --------------------------------------------------------------------

    def declarations(k):
        global entCount, offset
        if k.tokken == declare_tk:
            k = lex()
            if k.tokken == enddeclare_tk:
                k = lex()
                return k
            if k.tokken == id_tk:
                addEntity(k.charlex, 1, len(entitylist) + 1)
                print(offset, k.charlex)
                offset += 4
                k = lex()
                y = varlist(k)
                if y.tokken == enddeclare_tk:
                    k = lex()
                else:
                    print("Error: The keyword 'enddeclare' was expected.(LINE): ", line)
                return k
            else:
                print("Error: Variable name was expected.(LINE): ", line)
        return k

    # ----------------------------------------------------------------------

    def varlist(k):
        global offset
        while (k.tokken == comma_tk):
            k = lex()
            if k.tokken == id_tk:
                addEntity(k.charlex, 1, len(entitylist) + 1)
                print(offset, k.charlex)
                offset += 4
                k = lex()
            else:
                print("Error: Variable name expected.(LINE): ", line)
        return k

    # ---------------------------------------------------------------------

    def subprograms(k):
        while (k.tokken == procedure_tk or k.tokken == function_tk):
            k = func(k)
        return k

    # --------------------------------------------------------------------

    def func(k):
        if k.tokken == procedure_tk:
            k = lex()
            if k.tokken == id_tk:
                k = lex()
                y = funcbody(k)
                return y
            else:
                print("Error: Procedure name expected.(LINE): ", line)
        elif k.tokken == function_tk:
            k = lex()
            addEntity(k.charlex, 2, len(entitylist) + 1)
            addScope()
            funcName.append(k.charlex)
            genquad('begin_block', funcName[len(funcName) - 1], '_', '_')
            startQuad.append(asciiLine * 10 - 10)
            if k.tokken == id_tk:
                k = lex()
                y = funcbody(k)
                genquad('end_block', funcName[len(funcName) - 1], '_', '_')
                funcName.pop()
                delScope()
                return y
            else:
                print("Error: Function name expected.(LINE): ", line)
        else:
            print("Error: Func was expected.(LINE): ", line)
        return k

    # ------------------------------------------------------------------------

    def funcbody(k):
        y = formalpars(k)
        z = block(y)
        return z

    # ------------------------------------------------------------------------

    def formalpars(k):
        if k.tokken == opbrac1_tk:
            k = lex()
            y = formalparlist(k)
            if y.tokken == clbrac1_tk:
                k = lex()
            else:
                print("Error: Parentheses is open. Right parenthesis expected.(LINE): ", line)
        else:
            print("Else: Parentheses did not open. Left parenthesis expected.(LINE): ", line)
        return k

    # --------------------------------------------------------------------------------

    def formalparlist(k):

        if k.tokken == in_tk or k.tokken == inout_tk:
            y = formalparitem(k)
            while (y.tokken == comma_tk):
                k = lex()
                y = formalparitem(k)
            return y
        return k

    # -------------------------------------------------------------------------------

    def formalparitem(k):
        global offset
        if k.tokken == in_tk:
            k = lex()
            addEntity(k.charlex, 3, len(entitylist) + 1)
            print(offset, k.charlex)
            offset += 4
            addArgument(1, 0, len(argumentList) + 1)
            if k.tokken == id_tk:
                k = lex()
            else:
                print("Error: In name expected.(LINE): ", line)
        elif k.tokken == inout_tk:
            k = lex()
            addEntity(k.charlex, 3, len(entitylist) + 1)
            print(offset, k.charlex)
            offset += 4
            addArgument(2, 0, len(argumentList) + 1)
            if k.tokken == id_tk:
                k = lex()
            else:
                print("Error: Inout name expected.(LINE): ", line)
        else:
            print("Error: The keyword 'in' or the keyword 'inout' expected.(LINE): ", line)
        return k

    # ------------------------------------------------------------------------------

    def sequence(k):
        f = 0
        y = statement(k)
        if len(tmpArray) != 0:
            for n in tmpArray:
                if n is '(':
                    f += 1
            for na in range(0, f):
                parenthesis(tmpArray)
        calculate(tmpArray)
        tmpArray.clear()
        while (y.tokken == scol_tk or y.tokken == id_tk):
            if y.tokken == scol_tk:
                k = lex()
                y = statement(k)
            else:
                y = statement(y)
            calculate(tmpArray)
            tmpArray.clear()
        return y

    # --------------------------------------------------------------------------

    def bracket_seq(k):
        y = sequence(k)
        if y.tokken == clbrac3_tk:
            k = lex()
        else:
            print("Error: Bracket is open. Right bracket expected.(LINE): ", line)
        return k

    # ---------------------------------------------------------------------------

    def brack_or_stat(k):
        if k.tokken == opbrac3_tk:
            k = lex()
            y = bracket_seq(k)
            return y
        else:
            y = statement(k)
            if y.tokken == scol_tk:
                k = lex()
            else:
                print("Error: Semicolon was expected.(LINE): ", line)
            return k

    # ---------------------------------------------------------------------------

    def statement(k):
        if k.tokken == id_tk:
            tmpArray.append(k.charlex)
            k = lex()
            tmpArray.append(k.charlex)
            y = assignment_stat(k)
            return y
        elif k.tokken == if_tk:
            k = lex()
            y = if_stat(k)
            return y
        elif k.tokken == do_tk:
            k = lex()
            y = do_while_stat(k)
            return y
        elif k.tokken == while_tk:
            k = lex()
            y = while_stat(k)
            return y
        elif k.tokken == select_tk:
            k = lex()
            y = select_stat(k)
            return y
        elif k.tokken == exit_tk:
            y = exit_stat()
            return y
        elif k.tokken == return_tk:
            k = lex()
            y = return_stat(k)
            return y
        elif k.tokken == print_tk:
            k = lex()
            y = print_stat(k)
            return y
        elif k.tokken == call_tk:
            k = lex()
            fname = k.charlex
            y = call_stat(k)
            genquad('call', fname, '_', '_')
            return y
        return k

    # --------------------------------------------------------------------------

    def assignment_stat(k):
        if k.tokken == assign_tk:
            k = lex()
            tmpArray.append(k.charlex)
            y = expression(k)
        else:
            print("Error: Assignment operator was expected.(LINE): ", line)
            sys.exit()
        return y

    # ------------------------------------------------------------------------

    def if_stat(k):
        if k.tokken == opbrac1_tk:
            k = lex()
            tmpArray.append(k.charlex)
            trueList.append('if')
            falseList.append('if')
            y = condition(k)
            tmpArray.clear()
            makelist(asciiLine)
            if y.tokken == clbrac1_tk:
                genquad('jump', '_', '_', '_')
                backpatch(trueList, nextquad() * 10, 1)
                k = lex()
                y = brack_or_stat(k)
                k = elsepart(y)
                backpatch(falseList, nextquad() * 10, 0)

            else:
                print("Error: Parentheses is open. Right parenthesis expected.(LINE): ", line)
        else:
            print("Error: Parentheses did not open. Left parenthesis expected.(LINE): ", line)
        return k

    # --------------------------------------------------------------------------

    def elsepart(k):
        if k.tokken == else_tk:
            falseList.append(asciiLine)
            genquad('jump', '_', '_', '_')
            k = lex()
            y = brack_or_stat(k)
            backpatch(falseList, nextquad() * 10, 0)
            return y
        return k

    # --------------------------------------------------------------------------

    def while_stat(k):
        if k.tokken == opbrac1_tk:
            whileLine = (asciiLine - 1) * 10 + 10
            k = lex()
            trueList.append('if')
            falseList.append('if')
            tmpArray.append(k.charlex)
            y = condition(k)
            tmpArray.clear()
            makelist(asciiLine)
            if k.tokken == clbrac1_tk:
                genquad('jump', '_', '_', '_')
                backpatch(trueList, nextquad() * 10, 1)
                k = lex()
                y = brack_or_stat(k)
                genquad('jump', '_', '_', whileLine)
                backpatch(falseList, nextquad() * 10, 0)
            else:
                print("Error: Parentheses is open. Right parenthesis expected.(LINE): ", line)
            return y
        else:
            print("Error: Parentheses did not open. Left parenthesis expected.(LINE): ", line)

    # --------------------------------------------------------------------------

    def select_stat(k):
        if k.tokken == opbrac1_tk:
            k = lex()
            if k.tokken == id_tk:
                selectId = k.charlex
                k = lex()
                if k.tokken == clbrac1_tk:
                    k = lex()
                    while (k.tokken == digit_tk):
                        genquad('=', selectId, k.charlex, '_')
                        k = lex()
                        makelist(asciiLine)
                        if k.tokken == failassign_tk:
                            genquad('jump', '_', '_', '_')
                            backpatch(trueList, nextquad() * 10, 1)

                            k = lex()
                            k = brack_or_stat(k)
                            backpatch(falseList, nextquad() * 10, 0)
                            falseList.append(asciiLine)
                            genquad('jump', '_', '_', '_')
                        else:
                            print("Error: The symbol ':' was expected after the digit.(LINE): ", line)
                    if k.tokken == default_tk:
                        genquad('=', selectId, 'default', '_')
                        k = lex()
                        makelist(asciiLine)
                        if k.tokken == failassign_tk:
                            genquad('jump', '_', '_', '_')
                            backpatch(trueList, nextquad() * 10, 1)
                            k = lex()
                            k = brack_or_stat(k)
                            backpatch(falseList, nextquad() * 10, 0)

                        else:
                            print("Error: The symbol ':' was expected after the digit.(LINE): ", line)
                    else:
                        print("Error: The keyword 'default' was expected.(LINE): ", line)
                else:
                    print("Error: Parentheses is open. Right parenthesis expected.(LINE): ", line)
            else:
                print("Error: Select name was expected into parentheses.(LINE): ", line)
        else:
            print("Error: Parentheses did not open. Left parenthesis expected.(LINE): ", line)
        selectLine = (asciiLine - 1) * 10 + 10
        backpatch(falseList, selectLine, 0)
        return k

    # --------------------------------------------------------------------------

    def do_while_stat(k):
        doLine = (asciiLine - 1) * 10 + 10
        y = brack_or_stat(k)
        if y.tokken == while_tk:
            k = lex()
            if k.tokken == opbrac1_tk:
                k = lex()
                trueList.append('if')
                falseList.append('if')
                tmpArray.append(k.charlex)
                y = condition(k)
                tmpArray.clear()
                makelist(asciiLine)
                if y.tokken == clbrac1_tk:
                    genquad('jump', '_', '_', '_')
                    backpatch(trueList, doLine, 1)
                    backpatch(falseList, nextquad() * 10, 1)
                    k = lex()
                else:
                    print("Error: Parentheses is open. Right parenthesis expected.(LINE): ", line)
            else:
                print("Error: Parentheses did not open. Left parenthesis expected.(LINE): ", line)
        else:
            print("Error: The keyword 'while' was expected.(LINE): ", line)
        return k

    # --------------------------------------------------------------------------

    def exit_stat():
        k = lex()
        return k

    # --------------------------------------------------------------------------

    def return_stat(k):
        global tmp
        if k.tokken == opbrac1_tk:
            newtemp()
            tmpArray.append(newt[tmp])
            tmpArray.append(':=')
            k = lex()
            tmpArray.append(k.charlex)
            y = expression(k)
            if tmpArray[len(tmpArray) - 1] is ')':
                tmpArray.pop()
            calculate(tmpArray)
            genquad('retV', newt[tmp], '_', '_')
            tmp += 1
            tmpArray.clear()
            if y.tokken == clbrac1_tk:
                k = lex()
                return k
            else:
                print("Error: Parentheses is open. Right parenthesis expected.(LINE): ", line)

        else:
            print("Error: Parentheses did not open. Left parenthesis expected.(LINE): ", line)

    # --------------------------------------------------------------------------

    def print_stat(k):
        global tmp
        if k.tokken == opbrac1_tk:
            newtemp()
            tmpArray.append(newt[tmp])
            tmpArray.append(':=')
            k = lex()
            tmpArray.append(k.charlex)
            y = expression(k)
            if tmpArray[len(tmpArray) - 1] is ')':
                tmpArray.pop()
            calculate(tmpArray)
            genquad('out', newt[tmp], '_', '_')
            tmp += 1
            tmpArray.clear()
            if y.tokken == clbrac1_tk:
                k = lex()
                return k
            else:
                print("Error: Parentheses is open. Right parenthesis expected.(LINE): ", line)
        else:
            print("Error: Parentheses did not open. Left parenthesis expected.(LINE): ", line)



            # --------------------------------------------------------------------------

    # --------------------------------------------------------------------------

    def call_stat(k):
        if k.tokken == id_tk:
            k = lex()
            y = actualpars(k)
            return y
        else:
            print("Error: Call name expected.(LINE): ", line)

    # --------------------------------------------------------------------------
    def actualpars(k):
        if k.tokken == opbrac1_tk:
            k = lex()
            y = actualparlist(k)
            if y.tokken == clbrac1_tk:
                k = lex()
            else:
                print("Error: Parentheses is open. Right parenthesis expected.(LINE): ", line)
        else:
            print("Error: Parentheses did not open. Left parenthesis expected.(LINE): ", line)
        return k

    # --------------------------------------------------------------------------

    def actualparlist(k):
        if k.tokken == in_tk or k.tokken == inout_tk:

            y = actualparitem(k)
            while (y.tokken == comma_tk):
                k = lex()
                y = actualparitem(k)
            return y
        return k

    # --------------------------------------------------------------------------

    def actualparitem(k):
        if k.tokken == in_tk:
            # addEntity()
            # addArgument()
            k = lex()
            genquad('par', k.charlex, 'CV', '_')
            y = expression(k)
            return y
        elif k.tokken == inout_tk:
            # addEntity()
            # addArgument()
            k = lex()
            genquad('par', k.charlex, 'REF', '_')
            if k.tokken == id_tk:
                k = lex()
            else:
                print("Error: Inout name was expected.(LINE): ", line)
            return k
        else:
            print("Error: The keyword 'in' or the keyword 'inout' was expected.(LINE): ", line)

    # --------------------------------------------------------------------------

    def condition(k):
        y = boolterm(k)
        while (y.tokken == or_tk):
            tmpArray.clear()
            makelist(asciiLine)
            genquad('jump', '_', '_', (asciiLine + 1) * 10)
            k = lex()
            tmpArray.append(k.charlex)
            y = boolterm(k)
        return y

    # --------------------------------------------------------------------------

    def boolterm(k):
        y = boolfactor(k)
        condCalculate(tmpArray)
        while (y.tokken == and_tk):
            tmpArray.clear()
            makelist(asciiLine)
            genquad('jump', '_', '_', '_')
            backpatch(trueList, nextquad() * 10, 1)
            k = lex()
            tmpArray.append(k.charlex)
            y = boolfactor(k)
            condCalculate(tmpArray)
        return y

    # --------------------------------------------------------------------------

    def boolfactor(k):
        if k.tokken == not_tk:
            k = lex()
            if k.tokken == opbrac2_tk:
                k = lex()
                y = condition(k)
                if y.tokken == clbrac2_tk:
                    k = lex()
                else:
                    print("Error: Bracket is open. Right bracket expected.(LINE): ", line)
            else:
                print("Error: Bracket did not open. Left bracket expected.(LINE): ", line)
            return k
        elif k.tokken == opbrac2_tk:
            k = lex()
            y = condition(k)
            if y.tokken == clbrac2_tk:
                k = lex()
            else:
                print("Error: Bracket is open. Right bracket expected.(LINE): ", line)
            return k
        elif k.tokken == digit_tk:
            y = expression(k)
            z = relational_oper(y)
            w = expression(z)
            return w
        else:
            print("Error: The keyword 'not' or a left bracket or a relational operator was expected.(LINE): ", line)
        return k

    # --------------------------------------------------------------------------

    def expression(k):
        y = optional_sign(k)
        z = term(y)
        while (z.tokken == plus_tk or z.tokken == minus_tk):
            y = add_oper(k)
            z = term(y)
        return z

    # --------------------------------------------------------------------------

    def term(k):
        y = factor(k)
        while (y.tokken == multi_tk or y.tokken == division_tk):
            y = mul_oper(k)
            z = factor(y)
            return z
        return y

    # --------------------------------------------------------------------------

    def factor(k):
        if k.tokken == digit_tk:
            k = lex()
            if k.tokken == digit_tk or (k.tokken >= 3 and k.tokken <= 12) or k.tokken == 22:
                tmpArray.append(k.charlex)
        elif k.tokken == opbrac1_tk:
            k = lex()
            tmpArray.append(k.charlex)
            y = expression(k)
            if y.tokken == clbrac1_tk:
                k = lex()
                tmpArray.append(k.charlex)
            else:
                print("Error: Close parenthesis.(LINE): ", line)
        elif k.tokken == id_tk:
            k = lex()
            tmpArray.append(k.charlex)
            k = idtail(k)
        else:
            print("Error: digit or '(' or id was expected.(LINE): ", line)
        return k

    # --------------------------------------------------------------------------

    def idtail(k):
        if k.tokken == opbrac1_tk:
            y = actualpars(k)
            return y
        return k

    # --------------------------------------------------------------------------

    def relational_oper(k):
        global typeRel, strRel
        typeRel = 0
        strRel = ''
        if k.tokken == equal_tk:
            strRel = k.charlex
            k = lex()
            tmpArray.append(k.charlex)
        elif k.tokken == smaller_tk:
            strRel = k.charlex
            k = lex()
            tmpArray.append(k.charlex)
        elif k.tokken == smallequal_tk:
            typeRel = 1
            strRel = k.charlex
            tmpArray.pop()
            tmpArray.append('<')
            tmpArray.append('=')
            k = lex()
            tmpArray.append(k.charlex)
        elif k.tokken == different_tk:
            typeRel = 1
            strRel = k.charlex
            tmpArray.pop()
            tmpArray.append('<')
            tmpArray.append('>')
            k = lex()
            tmpArray.append(k.charlex)
        elif k.tokken == greatequal_tk:
            typeRel = 1
            strRel = k.charlex
            tmpArray.pop()
            tmpArray.append('>')
            tmpArray.append('=')
            k = lex()
            tmpArray.append(k.charlex)
        elif k.tokken == greater_tk:
            strRel = k.charlex
            k = lex()
            tmpArray.append(k.charlex)
        else:
            print("Error: Relational operator was expected.(LINE): ", line)
        return k

    # --------------------------------------------------------------------------

    def add_oper(k):
        if k.tokken == plus_tk:
            k = lex()
            tmpArray.append(k.charlex)
        elif k.tokken == minus_tk:
            k = lex()
            tmpArray.append(k.charlex)
        else:
            print("Error: Add or minus operator was expected.(LINE): ", line)
        return k

    # --------------------------------------------------------------------------

    def mul_oper(k):
        if k.tokken == multi_tk:
            k = lex()
            tmpArray.append(k.charlex)
        elif k.tokken == division_tk:
            k = lex()
            tmpArray.append(k.charlex)
        else:
            print("Error: Multi or division operator was expected.(LINE): ", line)
        return k

    # --------------------------------------------------------------------------

    def optional_sign(k):
        if k.tokken == plus_tk or k.tokken == minus_tk:
            y = add_oper(k)
            return y
        return k

    # --------------------------------------------------------------------------
    program(k)


def asciiWrite():
    r = open("foo.int", "w+")
    i = 0
    for i in range(0, len(asciiArray)):
        r.write('L' + str(i * 10) + ': ')
        json.dump(asciiArray[i], r)
        r.write('\n')
    r.close()


def nextquad():
    nextQ = len(asciiArray)
    return nextQ


def genquad(op, x, y, z):
    global asciiLine

    asciiArray.append([])
    asciiArray[asciiLine].append(op)
    asciiArray[asciiLine].append(x)
    asciiArray[asciiLine].append(y)
    asciiArray[asciiLine].append(z)

    asciiLine += 1


def newtemp():
    global counterTemp, offset
    counterTemp += 1
    newt.append('T_' + str(counterTemp))
    addEntity(newt[tmp], 4, len(entitylist) + 1)
    offset += 4
    print(offset, newt[tmp])


def emptylist():
    trueList = []
    falseList = []


def makelist(label):
    trueList.append(label - 1)
    falseList.append(label)


def merge(list1, list2):
    mergedList = list1 + list2
    return mergedList


def backpatch(list, z, c):
    if c == 1:  # true
        j = len(list) - 1
        if j > -1:
            while (list[j] != 'if'):
                asciiArray[list[j]][3] = z
                j -= 1
                if j < 0:
                    break
            del trueList[j:len(trueList)]
    else:
        j = len(list) - 1
        if j > -1:
            while (list[j] != 'if'):
                asciiArray[list[j]][3] = z
                j -= 1
                if j < 0:
                    break
            del falseList[j:len(falseList)]


def calcplus(array):
    global i, tmp
    variable = array[0]
    del array[0:2]
    newtemp()
    genquad(array[1], array[0], array[2], newt[tmp])
    i += 1
    tmp += 1
    del array[0:2]
    while len(array) != 1:
        newtemp()
        genquad(array[1], array[2], newt[tmp - 1], newt[tmp])
        del array[0:2]
        i += 1
        tmp += 1

    genquad(":=", variable, "_", newt[tmp - 1])
    return array


def calcmulti(array):
    variable = array[0]
    del array[0:2]
    global p, tmp
    p = 0
    for t in array:
        if array[p] is '*' or array[p] is '/':
            newtemp()
            genquad(array[p], array[p - 1], array[p + 1], newt[tmp])
            array[p] = newt[tmp]
            tmp += 1
            array.pop(p - 1)
            array.pop(p)
        p += 1
    array.insert(0, ":=")
    array.insert(0, variable)
    calcplus(array)
    return array


def calculate(array):
    if len(array) == 3:
        genquad(array[1], array[2], '_', array[0])
    if len(array) == 4:
        genquad(array[1], array[2] + array[3], '_', array[0])
    if len(array) == 5:
        genquad(array[3], array[2], array[4], array[0])
    if len(array) == 6:
        genquad(array[4], array[2] + array[3], array[5], array[0])
    if len(array) > 6:
        for l in array:
            if l is "*" or l is "/":
                global typeOfCalc
                typeOfCalc = 1
        if typeOfCalc == 0:
            calcplus(array)
        elif typeOfCalc == 1:
            calcmulti(array)


def condCalculate(array):
    global bi
    bi = 0
    array1 = []
    array2 = []
    array3 = copy.deepcopy(array)
    if array3[len(array3) - 1] == ")":
        bi = 1
        array3.pop()
    r = 0
    tmp1 = ''
    tmp2 = ''
    global tmp
    while array[r] is not '<' and array[r] is not '>' and array[r] is not '=':
        array1.append(array[r])
        r += 1
    for i in range(r + 1, len(array)):
        array2.append(array[i])
    if array2[0] is '=' or array2[0] is '>':
        array2.pop(0)
    if array2[len(array2) - 1] is ')':
        array2.pop()
    array1.insert(0, ':=')
    newtemp()
    tmp1 = newt[tmp]
    array1.insert(0, tmp1)
    tmp += 1
    array2.insert(0, ':=')
    newtemp()
    tmp2 = newt[tmp]
    array2.insert(0, tmp2)
    tmp += 1
    calculate(array1)
    calculate(array2)
    genquad(strRel, tmp1, tmp2, '_')


def parenthesis(array):
    global tmp
    point = 0
    point1 = 0
    parray = []
    for j in range(0, len(array)):
        if array[j] is '(':
            for h in range(j + 1, len(array)):
                if array[h] != ';' and array[h] != ')':
                    parray.append(array[h])
                else:
                    break
            break
    if len(parray) == 0:
        return
    newtemp()
    nt = newt[tmp]
    parray.insert(0, ':=')
    parray.insert(0, nt)
    calculate(parray)
    for r in range(0, len(tmpArray)):
        if tmpArray[r] is '(':
            point = r
            break
    for r1 in range(0, len(tmpArray)):
        if tmpArray[r1] is ')':
            point1 = r1
            break
    del tmpArray[point:point1]
    tmpArray[point] = nt

    parray.clear()
    tmp += 1

    if tmpArray[len(tmpArray) - 1] is ';' or tmpArray[len(tmpArray) - 1] is '}':
        tmpArray.pop()


def mipsWrite(array):
    global ci, parCounter, frCounter
    li = 1
    bei = -1
    arxi = 0
    telos = 0
    callName = ""
    f2.write("\t.globl " + array[0][1] + "\n")
    f2.write("\t.text \n")
    for i in range(0, len(array)):
        if array[i][0] != "begin_block" and array[i][0] != "end_block":
            f2.write("\tL" + str(li) + ": \n")
        if array[i][0] == "begin_block":
            bei += 1
            f2.write(array[i][1] + ":\n")
        if array[i][0] == "<" or array[i][0] == "<=" or array[i][0] == ">" or array[i][0] == ">=" or array[i][
            0] == "=" or array[i][0] == "<>":
            li += 1
            if array[i][1].isdigit():
                f2.write("\t\tmovi R[" + str(ci) + "]," + str(array[i][1]) + "\n")
                f2.write("\t\tmovi R[" + str(ci + 1) + "]," + str(array[i][3]) + "\n")
            else:
                f2.write("\t\tmovi R[" + str(ci) + "],M[600+" + str(offset) + "]\n")
                f2.write("\t\tmovi R[" + str(ci + 1) + "],M[600+" + str(offset + 4) + "]\n")

            f2.write("\t\tcmpi R[" + str(ci) + "],R[" + str(ci + 1) + "]\n")
            if array[i][0] == "<":
                f2.write("\t\tja L" + str(int(array[i][3] / 10)) + "\n")
            elif array[i][0] == "<=":
                f2.write("\t\tjae L" + str(int(array[i][3] / 10)) + "\n")
            elif array[i][0] == ">":
                f2.write("\t\tjb L" + str(int(array[i][3] / 10)) + "\n")
            elif array[i][0] == ">=":
                f2.write("\t\tjbe L" + str(int(array[i][3] / 10)) + "\n")
            elif array[i][0] == "=":
                f2.write("\t\tje L" + str(int(array[i][3] / 10)) + "\n")
            elif array[i][0] == "<>":
                f2.write("\t\tjne L" + str(int(array[i][3] / 10)) + "\n")
            ci += 1
        elif array[i][0] == ":=":
            li += 1
            if array[i][1].isdigit():
                f2.write("\t\tmovi R[" + str(ci) + "]," + str(array[i][1]) + "\n")
            else:
                f2.write("\t\tmovi R[" + str(ci) + "],M[600+" + str(offset) + "]\n")
            f2.write("\t\tmovi M[600+" + str(offset) + "],R[" + str(ci) + "]\n")
            ci += 1
        elif array[i][0] == "+" or array[i][0] == "-" or array[i][0] == "*" or array[i][0] == "/":
            li += 1
            ci += 1
            if array[i][1].isdigit():
                f2.write("\t\tmovi R[" + str(ci) + "]," + str(array[i][1]) + "\n")
                f2.write("\t\tmovi R[" + str(ci + 1) + "]," + str(array[i][2]) + "\n")
            else:
                f2.write("\t\tmovi R[" + str(ci) + "],M[600+" + str(offset) + "]\n")
                f2.write("\t\tmovi R[" + str(ci + 1) + "],M[600+" + str(offset + 4) + "]\n")
            if array[i][0] == "+":
                f2.write("\t\taddi R[" + str(ci + 2) + "],R[" + str(ci) + "],R[" + str(ci + 1) + "]\n")
            elif array[i][0] == "-":
                f2.write("\t\tsubi R[" + str(ci + 2) + "],R[" + str(ci) + "],R[" + str(ci + 1) + "]\n")
            elif array[i][0] == "*":
                f2.write("\t\tmuli R[" + str(ci + 2) + "],R[" + str(ci) + "],R[" + str(ci + 1) + "]\n")
            elif array[i][0] == "/":
                f2.write("\t\tdivi R[" + str(ci + 2) + "],R[" + str(ci) + "],R[" + str(ci + 1) + "]\n")
            f2.write("\t\tmovi M[600+" + str(offset) + "],R[" + str(ci + 2) + "]\n")
        elif array[i][0] == "jump":
            li += 1
            f2.write("\t\tjmp L" + str(int(array[i][3] / 10)) + "\n")
        elif array[i][0] == "out":
            li += 1
            ci += 1
            if array[i][1].isdigit():
                f2.write("\t\tmovi R[" + str(ci) + "]," + str(array[i][1]) + "\n")
            else:
                f2.write("\t\tmovi R[" + str(ci) + "],M[600+" + str(offset) + "]\n")
            f2.write("\t\touti R[" + str(ci) + "]\n")
        elif array[i][0] == "par":
            parCounter += 1
            li += 1
            if array[i][2] == "CV":
                if array[i][1].isdigit():
                    f2.write("\t\tmovi R[" + str(ci) + "]," + str(array[i][1]) + "\n")
                else:
                    f2.write("\t\tmovi R[" + str(ci) + "],M[600+" + str(offset) + "]\n")
                    f2.write("\t\tmovi M[" + str(framelength[frCounter] + 12 + 4 * parCounter) + "+R[0]],R[255]\n")
            elif array[i][2] == "REF":
                for ib in range(i, len(array)):
                    if array[ib][0] == "call":
                        callName = array[ib][1]
                        break
                for ic in range(0, len(array)):
                    if array[ic][0] == "begin_block" and array[ic][1] == callName:
                        arxi = ic
                    if array[ic][0] == "end_block" and array[ic][1] == callName:
                        telos = ic
                if ib > arxi and ib < telos:
                    f2.write("\t\tmovi R[255],R[0]\n")
                    f2.write("\t\tmovi R[254]," + str(offset) + "\n")
                    f2.write("\t\taddi R[255],R[254],R[255]\n")
                    f2.write("\t\tmovi R[1],M[R[255]]\n")
                    f2.write("\t\tmovi M[" + str(framelength[frCounter] + 12 + 4 * parCounter) + "+R[0]],R[1]\n")
                else:
                    f2.write("\t\tgnlvcode()\n")
                    f2.write("\t\tR[1],M[R[255]]\n")
                    f2.write("\t\tmovi M[" + str(framelength[frCounter] + 12 + 4 * parCounter) + "+R[0]],R[1]\n")
            frCounter += 1
            ci += 1
        elif array[i][0] == "retV":
            li += 1
            if array[i][1].isdigit():
                f2.write("\t\tmovi R[" + str(ci) + "]," + str(array[i][1]) + "\n")
            else:
                f2.write("\t\tmovi R[" + str(ci) + "],M[600+" + str(offset) + "]\n")
            f2.write("\t\tmovi R[255],M[8+R[0]]\n")
            f2.write("\t\tmovi M[R[255]],R[1]\n")
            ci += 1
        elif array[i][0] == "halt":
            li += 1
            f2.write("\t\tsyscall\n")
        elif array[i][0] == "end_block":
            ci = 0
            li += 1
            f2.write("\t\tjmp M[R[" + str(bei) + "]]\n")
            bei -= 1


k = lex()
tok = k.tokken
lektikh = k.charlex
syntax(k)


searchEntity("so")

def AsciiToC():
    parametroi = []
    w = open("foo.c", "w+")
    k = len(asciiArray)
    i = 1
    b = []
    w.write("int main()\n")
    w.write("{\n")
    w.write(
        "                                                                                                                        ")
    w.write("\n")
    w.write("  ")
    w.write("L_0:\n")

    def writer(line, str1):
        y = line[3]
        z = line[2]
        a = line[1]
        e = line[0]

        if str1 is "+" or str1 is "-" or str1 is "*" or str1 is "/":
            w.write(str(y))
            w.write("=")
            w.write(z)
            w.write(str1)
            w.write(a)
            w.write(";")
            w.write(" ")
            w.write("//(")
            b.append(y)
        elif str1 == "=" or str1 == ">" or str1 == "<" or str1 == "<>" or str1 == ">=" or str1 == "<=":
            w.write("if (")
            w.write(a)
            if str1 == "=":
                w.write(str1)
                w.write(str1)
                w.write(str(z))
                w.write(")")
                w.write(" goto ")
                w.write("L_")
                w.write(str(y))
                w.write(";")
                w.write(" ")
                w.write("//(")
            else:
                w.write(str1)
                w.write(str(z))
                w.write(")")
                w.write(" goto ")
                w.write("L_")
                w.write(str(y))
                w.write(";")
                w.write(" ")
                w.write("//(")
        elif str1 == ":=":
            w.write(str(y))
            w.write("=")
            w.write(str(a))
            w.write(";")
            w.write(" ")
            w.write("//(")
            b.append(str(y))
        elif str1 == "jump":
            w.write("goto ")
            w.write("L_")
            w.write(str(y))
            w.write(";")
            w.write(" ")
            w.write("//(")
        elif str1 == "out":
            w.write("print(")
            w.write(str(a))
            w.write(")")
            w.write(";")
            w.write(" ")
            w.write("//(")
        elif str1 == "retV":
            w.write("return(")
            w.write(str(a))
            w.write(")")
            w.write(";")
            w.write(" ")
            w.write("//(")
        elif str1 == "par":
            parametroi.append(str(a))
        elif str1 == "call":
            w.write(str(a))
            w.write("(")
            w.write(parametroi[0])
            i = 1
            while (i < len(parametroi)):
                w.write(", ")
                w.write(parametroi[i])
                i += 1
            w.write(")")
            w.write(";")
            w.write(" ")
            w.write("//(")
        elif str1 == "begin_block":
            w.write("{")
            w.write("  ")
            w.write("//(")
        elif str1 == "end_block":
            w.write("}")
            w.write("  ")
            w.write("//(")
        elif str1 == "halt":
            w.write("break")
            w.write(";")
            w.write(" ")
            w.write("//(")

    while (i < k - 1):
        w.write("  ")
        w.write("L_")
        p = (i * 10).__str__()
        w.write(p)
        w.write(":")
        w.write(" ")
        writer(asciiArray[i], asciiArray[i][0])
        w.write(str(asciiArray[i]))
        w.write(")")
        w.write("\n")
        i += 1
    mylist = []
    [mylist.append(item) for item in b if item not in mylist]
    w.write("  ")
    w.write("L_")
    p = ((k - 1) * 10).__str__()
    w.write(p)
    w.write(":")
    w.write(" ")
    w.write("{}\n")
    w.write("}\n")
    w.seek(17, 0)
    w.write("int ")
    w.write(mylist[0])
    i = 1
    while (i < len(mylist)):
        w.write(", ")
        w.write(mylist[i])
        i += 1
    w.write(";")
    w.close()


realoffset[len(realoffset) - 1] = offset
realoffset.reverse()
print("realoffset:", realoffset)
print("framelength:", framelength)
print("startQuad:", startQuad)

AsciiToC()
asciiWrite()
mipsWrite(asciiArray)

f.close()