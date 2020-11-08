///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package symbol;
//import java.util.*;
//import java.io.*;
//import java.util.Iterator;
//import java.lang.String;
//import symbol.Symbol;
//
//
//
//
///**
// *
// * @author sahilsheth
// */
//class SymbolTable {
//    Map <String, Symbol> table;
//    Iterator it = table.iterator();
//    Symbol first = it.next();
//    Symbol second = it.next();
//
//    public  void printTable()
//{
//        Iterator begin = table.iterator();
//	Iterator end = table.next();
//	System.out.println("Symbol table \n");
//	for (int i = 0; i < 52; i++)
//        {
//            System.out.println("-");
//        }
//		System.out.println("Identifier " + "Location " + "Type ");
//
//	while (begin != end)
//	{
//            System.out.println(it->first);
//            System.out.println(it->second.location);
//            System.out.println(it->second.type);
//
//        }
//
//		begin++;
//	}
//
//    String type(String lex)
//  {
//	return table.find(lex)->second.type;
//  }
//
//  String address(String &lex)
//{
//	Iterator begin = table.iterator();
//	Iterator end = table.next();
//	begin = table.find(lex);
//
//	while (begin != end)
//	{
//		if (begin->first == lex)
//		{
//			lex = toString(ittr->second.location);
//			return lex;
//		}
//
//		begin++;
//	}
//
//}
//}