package Fabrica;

import contabilidad.DaoBalance;
import contabilidad.DaoCuenta;
import contabilidad.DaoLibroDiario;
import marketing.DAOAnuncios;
import marketing.DAOEmpresa_publi;
import marketing.DAOOfertas;
import Pedido.DAOCalles;
import Pedido.DAOFormulario;
import Pedido.DAOUsuario;

public interface DAOs_Factory {
	
	/*
	 * ACTIVIDAD
	 */
	public DAOCalles crearDAOCalles();
	public DAOUsuario crearDAOUsuario();
	public DAOFormulario crearDAOFormulario();
	
	/*
	 * MARKETING
	 */
	public DAOAnuncios crearDAOAnuncios();
	public DAOEmpresa_publi crearDAOEmpresasPubli();
	public DAOOfertas crearDAOOfertas();
	/*
	 * CONTABILIDAD
	 */
	public DaoLibroDiario crearDAOLibroDiario();
	public DaoBalance crearDAOBalance();
	public DaoCuenta crearDAOCuenta();
	

}
