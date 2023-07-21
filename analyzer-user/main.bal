import ballerina/io;
import gayaldassanayake/code_complexity_analyzer as _;

public function main() {
    int y = 1;
    if y > 0 {
        io:println("Positive");
        if y == 1 {
            io:println("The smallest positive number");
        }
    } else if y < 0 {
        io:println("Negative");
    } else {
        io:println("Zero");
    }
}

function funcLogicalOr() returns boolean {
    return func1() || func2();
}

function func1() returns boolean {
    io:println("func1");
    return true;
}

function func2() returns boolean {
    io:println("func2");
    return false;
}

function funcMatch() {
    int x = 1;
    match x {
        1 => { io:println("1"); }
        2 => { io:println("2"); }
        3 => { io:println("3"); }
        _ => { io:println("default"); }
    }
}

function funcForLoop() {
    int x = 1;
    foreach int i in x...10 {
        int y = x + 1;
    }
}

function funcWhileLoop() {
    int x = 1;
    while x < 10 {
        int a = 1;
        while a < 10 {
            a += 1;
        }
        x +=1;
        int y = x + 1;
    }
}
