package com.thinking.machines.library.dl;
import java.util.*;
public interface AuthorDAOInterface 
{
public void add(AuthorInterface authorInterface)throws DAOException;
public void update(AuthorInterface authorInterface)throws DAOException;
public void delete(int code)throws DAOException;
public LinkedList<AuthorInterface> getAll() throws DAOException;
public LinkedList<AuthorInterface> getAll(Author.ATTRIBUTE sortBy) throws DAOException;
public AuthorInterface getByCode(int code)throws DAOException;
public AuthorInterface getByName(String name)throws DAOException;
public long getCount() throws DAOException;
}