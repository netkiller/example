#!/usr/bin/rdmd
import std.stdio;
import std.getopt; 
string data = "file.dat"; 
int length = 24; 
bool verbose; 
enum Color { no, yes }; 

Color color; 
void main(string[] args) { 
	getopt( args, "length", &length, // numeric 
	"file", &data, // string 
	"verbose", &verbose, // flag 
	"color", &color); // enum
	writeln("Hello, world!");
}

