package application;
	
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Main extends Application {
	ArrayList<Sandwich> orderList = new ArrayList<Sandwich>();
	double totalPrice = 0;
	
	@Override
	public void start(Stage primaryStage) {
		
		//	ORDER SCENE
		VBox orderVert = new VBox();
		HBox funcHorz = new HBox();
		HBox menuHorz = new HBox();
		VBox ingriVert = new VBox();							// GUI organizing
		HBox totalHorz = new HBox();
		VBox summaryVert = new VBox();
		Scene CheckoutScene = new Scene(summaryVert,800,500);
		Scene orderScene = new Scene(orderVert,800,500);
		
		ListView<String> sandOption = new ListView<String>();
		CheckBox togglelettuce = new CheckBox("Lettuce");
		CheckBox toggleTomato = new CheckBox("Tomato");
		CheckBox toggleBacon = new CheckBox("Bacon");
		CheckBox toggleMustard = new CheckBox("Mustard");
		CheckBox toggleMayo = new CheckBox("Mayo");
		CheckBox togglePickles = new CheckBox("Pickles");			// GUI interactions
		RadioButton breadType1 = new RadioButton("Wheat");
      	  	RadioButton breadType2 = new RadioButton("Foccacia");
       		RadioButton breadType3 = new RadioButton("Sourdough");
       		ToggleGroup radioGroup = new ToggleGroup();
       		Button checkout = new Button("Go to Checkout");
		Button addSand = new Button("Add Sandwich");
		Text total = new Text();
		
		Label orderLabel = new Label("ORDER HERE");
		Label spaceLabel1 = new Label(" ");
		Label mainLabel = new Label("Select a sandwich ");
		Label sideLabel = new Label(" Select additional ingredients ");
		Label breadLabel = new Label("	Select bread type ");				// GUI labels
		Label summaryLabel = new Label("ORDER SUMMARY");
		Label spaceLabel2 = new Label(" ");
		Label totalLabel = new Label("		Total:");
		
		TableView tableView = new TableView();
		TableColumn<String, Sandwich> column1 = new TableColumn<>("Sandwich");
	    	column1.setCellValueFactory(new PropertyValueFactory<>("nameAndIngredients"));
	    	TableColumn<String, Sandwich> column2 = new TableColumn<>("Price");					// Table for summary
	   	column2.setCellValueFactory(new PropertyValueFactory<>("strPrice"));
	   	tableView.getColumns().add(column1);
		tableView.getColumns().add(column2);
		
		primaryStage.setTitle("Valencia Sandwich Shop");
		primaryStage.setScene(orderScene);
		primaryStage.show();
		
		//		ORDER SCENE
		orderVert.getChildren().add(orderLabel);
		orderLabel.setFont(new Font("Arial", 24));
		orderVert.getChildren().add(spaceLabel1);
		orderVert.getChildren().add(menuHorz);
		orderVert.getChildren().add(funcHorz);
		
		menuHorz.getChildren().add(mainLabel);
		menuHorz.getChildren().add(sandOption);
		sandOption.setPrefSize(210, 280);
		sandOption.getItems().add("Turkey Club				    $8");
		sandOption.getItems().add("Ham Club					    $9");
		sandOption.getItems().add("Chicken Club				    $7");
		sandOption.getItems().add("Cheddar Grilled Cheese		    $4");
		sandOption.getItems().add("Swiss Grilled Cheese		    $5");
		sandOption.getItems().add("Mozz. Grilled Cheese		    $6");				// Sandwich list options
		sandOption.getItems().add("Turkey Panini				    $8");
		sandOption.getItems().add("Ham Panini				    $9");
		sandOption.getItems().add("Chicken Panini				    $7");
		sandOption.getItems().add("Scrambled Egg Sandwich	    $4");
		sandOption.getItems().add("Over-easy Egg Sandwich		    $5");
		sandOption.getItems().add("Over-hard Egg Sandwich		    $6");
		
		menuHorz.getChildren().add(sideLabel);
		menuHorz.getChildren().add(ingriVert);
		ingriVert.getChildren().add(togglelettuce);
		ingriVert.getChildren().add(toggleTomato);
		ingriVert.getChildren().add(toggleBacon);				// Side ingredient toggles
		ingriVert.getChildren().add(toggleMustard);
		ingriVert.getChildren().add(toggleMayo);
		ingriVert.getChildren().add(togglePickles);
        
		breadType1.setToggleGroup(radioGroup);
		breadType2.setToggleGroup(radioGroup);
		breadType3.setToggleGroup(radioGroup);
		breadType1.setUserData("Wheat");					// Bread toggle
		breadType2.setUserData("Foccacia");
		breadType3.setUserData("Sourdough");
		VBox breadVert = new VBox(breadType1, breadType2, breadType3);
		menuHorz.getChildren().add(breadLabel);
		menuHorz.getChildren().add(breadVert);
		funcHorz.getChildren().add(addSand);
		
		addSand.setOnAction(actionEvent ->  {
			if (radioGroup.getSelectedToggle() != null && sandOption.getSelectionModel().getSelectedIndex() != -1) {
				int selectedIndex = sandOption.getSelectionModel().getSelectedIndex();
				String breadSelected = radioGroup.getSelectedToggle().getUserData().toString();
				boolean hasLettuce = togglelettuce.isSelected();
				boolean hasTomato = toggleTomato.isSelected();
				boolean hasBacon = toggleBacon.isSelected();									// Add Sandwich Button
				boolean hasMustard = toggleMustard.isSelected();
				boolean hasMayo = toggleMayo.isSelected();
				boolean hasPickles= togglePickles.isSelected();
				Sandwich sand1 = new Sandwich(selectedIndex, breadSelected, hasLettuce, 
								hasTomato, hasBacon, hasMustard, hasMayo, hasPickles);
				orderList.add(sand1);
			}
		});
		
		funcHorz.getChildren().add(checkout);
		checkout.setOnAction(actionEvent ->  {
			if (radioGroup.getSelectedToggle() != null && sandOption.getSelectionModel().getSelectedIndex() != -1) {
				for (Sandwich sand : orderList){
					tableView.getItems().add(sand);
					totalPrice = totalPrice + sand.sandPrice;
				}																// Checkout Button
				primaryStage.setScene(CheckoutScene);
				total.setText("	$" + totalPrice + "0");
			}
		});
		
		//		CHECKOUT SCENE
		summaryVert.getChildren().add(summaryLabel);
		summaryLabel.setFont(new Font("Arial", 24));
		summaryVert.getChildren().add(spaceLabel2);
		summaryVert.getChildren().add(tableView);
		summaryVert.getChildren().add(totalHorz);
		totalLabel.setFont(new Font(20));
		totalHorz.getChildren().add(totalLabel);
		total.setFont(new Font(20));
		totalHorz.getChildren().add(total);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
