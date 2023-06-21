package General;



public class Main_General {
	public static void main(String[] args) {
		Controlador_General controller = new Controlador_General();
		GeneralGUI vista = new GeneralGUI(controller); 
		vista.setVisible(true);
			
		}
}
