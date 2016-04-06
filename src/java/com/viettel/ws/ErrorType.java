/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.viettel.ws;

/**
 *
 * @author Administrator
 */
public class ErrorType {
   public ErrorType( int id,String name,int length ,String type,String table)
    {
        this.Id=id;
        this.Name=name;
        this.Length=length;
        this.Table=table;
        this.Type=type;
    }
    public int Id;
    public String Name;
    public  int Length;
    public String Type;
    public String Table; 
}
