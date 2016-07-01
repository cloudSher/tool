package com.sher.tool.base.test.corba.helloapp;

/**
* helloapp/HelloHolder.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��idl
* 2016��6��28�� ���ڶ� ����12ʱ18��21�� GMT+08:00
*/

public final class HelloHolder implements org.omg.CORBA.portable.Streamable
{
  public Hello value = null;

  public HelloHolder ()
  {
  }

  public HelloHolder (Hello initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = HelloHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    HelloHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return HelloHelper.type ();
  }

}
