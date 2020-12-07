package ViewFXML;

import javax.swing.JOptionPane;

import com.google.protobuf.TextFormatParseInfoTree;

import Dao.ClienteJpaDAO;
import Model.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class FXMLClienteController {

	// ********************************************** //
	// METODOS CONTROLE - ( PERSISTIR TABELA CLIENTE )
	// ********************************************** //

	@FXML
	private TextField txtid;
	@FXML
	private TextField txtnome;
	@FXML
	private TextField txtemail;

	@FXML
	private Button btsalvar;
	@FXML
	private Button btRemover;
	@FXML
	private Button btBuscar;

	public FXMLClienteController() {
	}

	@FXML
	public void OnbtsalvarAction() {

		Cliente cliente = new Cliente();

		cliente.setId(Integer.parseInt(txtid.getText()));
		cliente.setNome(txtnome.getText());
		cliente.setEmail(txtemail.getText());

		ClienteJpaDAO.getInstance().persist(cliente);
		clearFields();

	}

	@FXML
	public void onbtUpdAction() {

		Cliente cliente = new Cliente();

		cliente.setId(Integer.parseInt(txtid.getText()));
		cliente.setNome(txtnome.getText());
		cliente.setEmail(txtemail.getText());

		ClienteJpaDAO.getInstance().merge(cliente);
		clearFields();
		
	}

	@FXML
	public void onbtnRemoveAction() {

		ClienteJpaDAO.getInstance().removerById(Integer.parseInt(txtid.getText()));
		clearFields();

	}

	@FXML
	private void onbtnBuscarAction() {

		int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do cliente"));

		Cliente cliente = ClienteJpaDAO.getInstance().getById(id);

		txtnome.setText(cliente.getNome());
		txtemail.setText(cliente.getEmail());

	}

	private void clearFields() {
		txtid.setText("");
		txtnome.setText("");
		txtemail.setText("");
	}

}
