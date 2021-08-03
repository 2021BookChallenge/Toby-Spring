package com.example.tobyspring.chapter1.config;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {

	Connection makeConnection() throws ClassNotFoundException, SQLException;
}
