package Fabrica;

import marketing.DAOAnuncios;
import marketing.DAOEmpresa_publi;
import marketing.DAOOfertas;
import contabilidad.DaoBalance;
import contabilidad.DaoCuenta;
import contabilidad.DaoLibroDiario;
import Pedido.DAOCalles;
import Pedido.DAOFormulario;
import Pedido.DAOUsuario;

public class FactoryDAOs_General implements DAOs_Factory{

	@Override
	public DAOCalles crearDAOCalles() {
		// TODO Auto-generated method stub
		return new DAOCalles();
	}

	@Override
	public DAOUsuario crearDAOUsuario() {
		// TODO Auto-generated method stub
		return new DAOUsuario();
	}

	@Override
	public DAOFormulario crearDAOFormulario() {
		// TODO Auto-generated method stub
		return new DAOFormulario();
	}

	@Override
	public DAOAnuncios crearDAOAnuncios() {
		// TODO Auto-generated method stub
		return new DAOAnuncios();
	}

	@Override
	public DAOEmpresa_publi crearDAOEmpresasPubli() {
		// TODO Auto-generated method stub
		return new DAOEmpresa_publi();
	}

	@Override
	public DAOOfertas crearDAOOfertas() {
		// TODO Auto-generated method stub
		return new DAOOfertas();
	}

	@Override
	public DaoLibroDiario crearDAOLibroDiario() {
		// TODO Auto-generated method stub
		return new DaoLibroDiario();
	}

	@Override
	public DaoBalance crearDAOBalance() {
		// TODO Auto-generated method stub
		return new DaoBalance();
	}

	@Override
	public DaoCuenta crearDAOCuenta() {
		// TODO Auto-generated method stub
		return new DaoCuenta();
	}

}
