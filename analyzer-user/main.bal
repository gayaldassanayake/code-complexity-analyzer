import ballerina/io;
import gayaldassanayake/code_complexity_analyzer as _;

public function main() {
    boolean x = func1() || func2();
    int y = 1;
    if y == 1 {
        io:println("1");
    } else if y == 2 {
        io:println("2");
    } else {
       io:println("3");
    }
    funcMatch();
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
