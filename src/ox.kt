var oxTable : Array<Array<Char>> = arrayOf(
    arrayOf(' ', '1', '2', '3'),
    arrayOf('1', '-', '-', '-'),
    arrayOf('2', '-', '-', '-'),
    arrayOf('3', '-', '-', '-')
)

var player = 'O'    //start with player 'O'
var gameStatus = 0  //check win or draw
var turnCount = 0   //count for check draw

//print oxTable Array 2D
fun table(){
    for(row : Array<Char> in oxTable){
        for(col : Char in row){
            print(col)
        }
        println()
    }
}

fun switchPlayer() {
    if(player == 'O'){
        player = 'X'
    }else{
        player = 'O'
    }
}

//Check Game Win or Draw
fun checkGameStatus(rowsInput : Int, colInput : Int) {
    turnCount++

    if(checkWin(rowsInput, colInput)) {
        gameStatus = 1
    }

    if(checkDraw()) {
        gameStatus = 2
    }
}

fun checkDraw(): Boolean {
    if(turnCount < 9) {
        return false
    }
    return true
}

fun checkWin(rowCheck : Int, colCheck : Int) : Boolean {
    var colStatus = true
    for(i in 1..3) {
        if(oxTable[i][colCheck] != player) {
            colStatus = false
        }
    }
    if(colStatus){
        return true
    }

    var rowStatus = true
    for(i in 1..3) {
        if(oxTable[rowCheck][i] != player) {
            rowStatus = false
        }
    }
    if(rowStatus) {
        return true
    }

    //check diagonal
    var esStatus = true
    for(i in 1..3) {
        if(oxTable[i][i] != player) {
            esStatus = false
        }
    }
    if(esStatus){
        return true
    }

    var ssStatus = true
    for(i in 0..2) {
        if(oxTable[i + 1][3 - i] != player) {
            ssStatus = false
        }
    }
    if(ssStatus){
        return true
    }

    return false
}

fun main(){
    println("Welcome to  OX Game!")
    table()

    try{
        while(true){

            println("$player turn")
            print("Plase input Row Col :")

            // input from user
            val input : String? = readLine()
            val lspilt : List<String>? = input?.split(" ")

            if(lspilt?.size != 2) {
                println("Error: Plase input Row & Col again")
                continue
            }
            val inputRow : Int = lspilt.get(0).toInt()
            val inputCol : Int = lspilt.get(1).toInt()

            oxTable[inputRow][inputCol] = player
            table()
            println("Row $inputRow Col $inputCol")

            checkGameStatus(inputRow, inputCol)
            if(gameStatus == 1) {
                println("$player Win!")
                break
            }else if(gameStatus == 2) {
                print("Draw Game!")
                break
            }

            switchPlayer()
        }
    }catch (e: Throwable){
        println("Error: Can't put this index in Row & Col")
    }
}


