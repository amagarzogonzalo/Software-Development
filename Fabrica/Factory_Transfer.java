package Fabrica;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import General.NoFoundException;
import General.Transfer;


public interface Factory_Transfer {
	public Transfer crearTransfer(ArrayList<Object> info) throws NoFoundException;
	public Transfer cargarTransfer(BufferedReader lector, String linea) throws NoFoundException;
	public String getInfo();
}

