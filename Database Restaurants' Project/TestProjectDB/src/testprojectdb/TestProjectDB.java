/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojectdb;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author Hp
 */
public class TestProjectDB extends Application {

    String uname;

    @Override
    public void start(Stage primaryStage) {

        primaryStage.show();

        Scene tobeBuilt;
        VBox tbbvb = new VBox();
        Label oops = new Label("Oops! Looks like this page has not been developed yet!");
        Button backfromtbb = new Button("Back");
        tbbvb.getChildren().addAll(oops, backfromtbb);
        tobeBuilt = new Scene(tbbvb, 400, 400);

        Scene loginScene;
        ChoiceBox usertype = new ChoiceBox();
        usertype.getItems().addAll("User", "Admin", "Authority");
        usertype.setValue("User");
        usertype.setId("usertypecb");
        TextField usernameli = new TextField();
        usernameli.setPromptText("Enter Username...");
        PasswordField passwordli = new PasswordField();
        passwordli.setPromptText("Enter Password");
        Button liokButton = new Button("Login");
        Label suprompt = new Label("Don't have an account? Sign up instead!");
        suprompt.setId("NormalText");
        Button toSignup = new Button("Sign Up");
        Label enterunamelabel = new Label("Enter your username:");
        enterunamelabel.setId("NormalText");
        Label loginpasswordlabel = new Label("Enter your Password:");
        loginpasswordlabel.setId("NormalText");
        VBox loginLayout = new VBox();
        loginLayout.getChildren().addAll(usertype, enterunamelabel, usernameli, loginpasswordlabel, passwordli, liokButton, suprompt, toSignup);
        loginLayout.setAlignment(Pos.CENTER);
        loginLayout.setSpacing(10);
        loginLayout.setId("loginLayout");
        loginScene = new Scene(loginLayout, 500, 500);
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Welcome!");

        loginScene.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());

        Button logoutloc = new Button("Logout");
        logoutloc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(loginScene);
            }
        });

        //HomeScene
        Scene HomeScene;
        GridPane hsGridPane = new GridPane();
        hsGridPane.setVgap(20);
        hsGridPane.setHgap(20);
        HomeScene = new Scene(hsGridPane, 500, 570);

        HomeScene.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());
        hsGridPane.setId("homeScene");

        Button PlanMeal = new Button("Plan a Meal!");
        PlanMeal.setPrefSize(250, 40);
        hsGridPane.add(PlanMeal, 5, 4);
        Button ShowRestB = new Button("See All Restaurants!");
        ShowRestB.setPrefSize(250, 40);
        hsGridPane.add(ShowRestB, 5, 6);
        Button RateBranch = new Button("Rate a Branch!");
        RateBranch.setPrefSize(250, 40);
        hsGridPane.add(RateBranch, 5, 8);
        Button RateMeal = new Button("Rate a Meal!");
        RateMeal.setPrefSize(250, 40);
        hsGridPane.add(RateMeal, 5, 10);
        Button ShowOffer = new Button("See Latest Offers!");
        ShowOffer.setPrefSize(250, 40);
        hsGridPane.add(ShowOffer, 5, 12);

        Button ShowOrder = new Button("See Orders!");
        ShowOrder.setPrefSize(250, 40);
        hsGridPane.add(ShowOrder, 5, 14);

        PlanMeal.setId("NormalButtonsStyle");
        ShowRestB.setId("NormalButtonsStyle");
        RateBranch.setId("NormalButtonsStyle");
        RateMeal.setId("NormalButtonsStyle");
        ShowOffer.setId("NormalButtonsStyle");
        ShowOrder.setId("NormalButtonsStyle");

        Button logouthome = new Button("Logout");
        logouthome.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(loginScene);
            }
        });

        hsGridPane.add(logouthome, 6, 14);

        PlanMeal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Scene scene;
                GridPane root = new GridPane();
                root.setVgap(10);
                root.setHgap(10);
                root.setAlignment(Pos.CENTER);
                scene = new Scene(root, 500, 500);
                scene.setFill(Color.BLACK);

                root.setId("PlanMealBackground");
                scene.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());

                ChoiceBox cb = new ChoiceBox();
                cb.setId("NormalButtonsStyle");

                ConnectionClass conC = new ConnectionClass();
                Connection con = conC.getConnection();
                List listlocs = new ArrayList();
                try {

                    String sql = "SELECT LOCATION_NAME FROM LOCATIONS";
                    PreparedStatement pst = con.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        listlocs.add(rs.getString(1));
                    }
                    pst.close();
                    con.close();
                    conC.closeConnection();

                    //System.out.println(uname);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                cb.getItems().addAll(listlocs);
                cb.getItems().add("Anywhere Will Do!");
                cb.setValue("Anywhere Will Do!");
                //cb.setValue(listlocs.get(0));
                cb.setPrefSize(250, 40);
                root.add(cb, 1, 2);

                Label selectLocationprompt = new Label("Where do you want to eat?");
                selectLocationprompt.setId("NormalText");
                root.add(selectLocationprompt, 1, 0);
                ChoiceBox cb2 = new ChoiceBox();
                cb2.setId("NormalButtonsStyle");

                conC = new ConnectionClass();
                con = conC.getConnection();
                List listitems = new ArrayList();
                try {

                    String sql = "SELECT DISTINCT ITEM_TYPE FROM MENU_ITEMS";
                    PreparedStatement pst = con.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        listitems.add(rs.getString(1));
                    }
                    pst.close();
                    con.close();
                    conC.closeConnection();

                    //System.out.println(uname);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                cb2.getItems().addAll(listitems);
                //cb2.setValue(listitems.get(0));
                cb2.getItems().add("Anything Will Do!");
                cb2.setValue("Anything Will Do!");
                cb2.setPrefSize(250, 40);
                root.add(cb2, 1, 4);

                Label selectFoodprompt = new Label("What do you want to eat?");
                selectFoodprompt.setId("NormalText");
                root.add(selectFoodprompt, 1, 3);

                TextField pricePlan = new TextField();
                pricePlan.setPrefSize(250, 40);
                pricePlan.setId("NormalButtonsStyle");
                root.add(pricePlan, 1, 6);

                Label WritePriceprompt = new Label("What is your highest budget?");
                WritePriceprompt.setId("NormalText");
                root.add(WritePriceprompt, 1, 5);

                Button backtoHomeScene = new Button("Back");
                backtoHomeScene.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setScene(HomeScene);
                    }
                });
                root.add(backtoHomeScene, 2, 12);

                Button b2 = new Button("All Set!");
                b2.setPrefSize(100, 40);
                root.add(b2, 2, 6);

                b2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        int budget = 0;
                        if (pricePlan.getText() == null || pricePlan.getText().trim().isEmpty()) {
                            budget = 99999;
                        } else {
                            budget = Integer.parseInt(pricePlan.getText());
                        }

                        ConnectionClass conC = new ConnectionClass();
                        Connection con = conC.getConnection();
                        ObservableList<Result> results = FXCollections.observableArrayList();

                        try {
                            ///insert query here
                            //String sql = "select r.RESTAURANT_NAME, rb.ADDRESS, mi.ITEM_NAME, bm.PRICE, bm.OFFER from BRANCH_MENUS bm join restaurants r using(rest_id) join locations l using(location_id) join MENU_ITEMS mi using(item_id) join RESTAURANTS_BRANCHES rb using(rest_id, location_id) where l.LOCATION_NAME = ? AND mi.ITEM_TYPE = ? AND bm.PRICE <= ?";
                            String sql;
                            PreparedStatement pst = null;
                            if ((String) cb.getValue() != "Anywhere Will Do!" && (String) cb2.getValue() != "Anything Will Do!") {
                                sql = "select r.RESTAURANT_NAME, l.Location_Name, rb.ADDRESS, i.ITEM_TYPE, i.ITEM_NAME, bm.PRICE, bm.OFFER, GET_BRANCHMENU_AVGRATING(r.RESTAURANT_NAME, l.LOCATION_NAME, i.ITEM_ID) from BRANCH_MENUS bm join RESTAURANTS r on (bm.REST_ID = r.REST_ID) join LOCATIONS l on (l.LOCATION_ID = bm.LOCATION_ID) join MENU_ITEMS i on (i.ITEM_ID = bm.ITEM_ID) join RESTAURANTS_BRANCHES rb on (bm.REST_ID = rb.REST_ID and bm.LOCATION_ID = rb.LOCATION_ID) where l.LOCATION_NAME = ? AND i.ITEM_TYPE = ? AND bm.PRICE <= ?";
                                pst = con.prepareStatement(sql);
                                pst.setString(1, (String) cb.getValue());
                                pst.setString(2, (String) cb2.getValue());
                                pst.setInt(3, budget);
                            } else if ((String) cb.getValue() != "Anywhere Will Do!" && (String) cb2.getValue() == "Anything Will Do!") {
                                sql = "select r.RESTAURANT_NAME, l.Location_Name, rb.ADDRESS, i.ITEM_TYPE, i.ITEM_NAME, bm.PRICE, bm.OFFER, GET_BRANCHMENU_AVGRATING(r.RESTAURANT_NAME, l.LOCATION_NAME, i.ITEM_ID) from BRANCH_MENUS bm join RESTAURANTS r on (bm.REST_ID = r.REST_ID) join LOCATIONS l on (l.LOCATION_ID = bm.LOCATION_ID) join MENU_ITEMS i on (i.ITEM_ID = bm.ITEM_ID) join RESTAURANTS_BRANCHES rb on (bm.REST_ID = rb.REST_ID and bm.LOCATION_ID = rb.LOCATION_ID) where l.LOCATION_NAME = ? AND bm.PRICE <= ?";
                                pst = con.prepareStatement(sql);
                                pst.setString(1, (String) cb.getValue());
                                pst.setInt(2, budget);
                            } else if ((String) cb.getValue() == "Anywhere Will Do!" && (String) cb2.getValue() != "Anything Will Do!") {
                                sql = "select r.RESTAURANT_NAME, l.Location_Name, rb.ADDRESS, i.ITEM_TYPE, i.ITEM_NAME, bm.PRICE, bm.OFFER, GET_BRANCHMENU_AVGRATING(r.RESTAURANT_NAME, l.LOCATION_NAME, i.ITEM_ID) from BRANCH_MENUS bm join RESTAURANTS r on (bm.REST_ID = r.REST_ID) join LOCATIONS l on (l.LOCATION_ID = bm.LOCATION_ID) join MENU_ITEMS i on (i.ITEM_ID = bm.ITEM_ID) join RESTAURANTS_BRANCHES rb on (bm.REST_ID = rb.REST_ID and bm.LOCATION_ID = rb.LOCATION_ID)  where i.ITEM_TYPE = ? AND bm.PRICE <= ?";
                                pst = con.prepareStatement(sql);
                                pst.setString(1, (String) cb2.getValue());
                                pst.setInt(2, budget);
                            } else if ((String) cb.getValue() == "Anywhere Will Do!" && (String) cb2.getValue() == "Anything Will Do!") {
                                sql = "select r.RESTAURANT_NAME, l.Location_Name, rb.ADDRESS, i.ITEM_TYPE, i.ITEM_NAME, bm.PRICE, bm.OFFER, GET_BRANCHMENU_AVGRATING(r.RESTAURANT_NAME, l.LOCATION_NAME, i.ITEM_ID) from BRANCH_MENUS bm join RESTAURANTS r on (bm.REST_ID = r.REST_ID) join LOCATIONS l on (l.LOCATION_ID = bm.LOCATION_ID) join MENU_ITEMS i on (i.ITEM_ID = bm.ITEM_ID) join RESTAURANTS_BRANCHES rb on (bm.REST_ID = rb.REST_ID and bm.LOCATION_ID = rb.LOCATION_ID)  where bm.PRICE <= ?";
                                pst = con.prepareStatement(sql);
                                pst.setInt(1, budget);
                            }

                            if (pst == null) {
                                System.out.println("Genjam lagse");
                            }
                            ResultSet rs = pst.executeQuery();

                            while (rs.next()) {

                                /*System.out.print(rs.getString(1)+ " ") ;
                                System.out.print(rs.getString(2)+ " ") ;
                                System.out.print(rs.getString(3)+ " ") ;
                                System.out.print(rs.getInt(4)+ " ") ;
                                System.out.print(rs.getString(5)+ " ") ;
                                System.out.println("");*/
                                String str1 = rs.getString(1);
                                String str2 = rs.getString(2);
                                String str3 = rs.getString(3);
                                String str4 = rs.getString(4);
                                String str5 = rs.getString(5);
                                String str6 = Integer.toString(rs.getInt(6));
                                String str7 = rs.getString(7);
                                String str8 = Double.toString(rs.getDouble(8));

                                results.add(new Result(str1, str2, str3, str4, str5, str6, str7, str8));

                            }

                            pst.close();
                            con.close();
                            conC.closeConnection();

                        } catch (SQLException e) {
                            System.out.println("Check it from console");
                            e.printStackTrace();
                        }

                        TableView<Result> table;
                        primaryStage.setTitle("Results");

                        TableColumn<Result, String> rest_nameColumn = new TableColumn<>("Restaurant_Name");
                        rest_nameColumn.setMinWidth(200);
                        rest_nameColumn.setCellValueFactory(new PropertyValueFactory<>("Restaurant_Name"));

                        TableColumn<Result, String> loc_nameColumn = new TableColumn<>("Location_Name");
                        loc_nameColumn.setMinWidth(200);
                        loc_nameColumn.setCellValueFactory(new PropertyValueFactory<>("Location_Name"));

                        TableColumn<Result, String> AddressColumn = new TableColumn<>("Address");
                        AddressColumn.setMinWidth(200);
                        AddressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));

                        TableColumn<Result, String> Item_TypeColumn = new TableColumn<>("Item_Type");
                        Item_TypeColumn.setMinWidth(200);
                        Item_TypeColumn.setCellValueFactory(new PropertyValueFactory<>("Item_Type"));

                        TableColumn<Result, String> Item_NameColumn = new TableColumn<>("Item_Name");
                        Item_NameColumn.setMinWidth(200);
                        Item_NameColumn.setCellValueFactory(new PropertyValueFactory<>("Item_Name"));

                        TableColumn<Result, String> PriceColumn = new TableColumn<>("Price");
                        PriceColumn.setMinWidth(100);
                        PriceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));

                        TableColumn<Result, String> OfferColumn = new TableColumn<>("Offer");
                        OfferColumn.setMinWidth(200);
                        OfferColumn.setCellValueFactory(new PropertyValueFactory<>("Offer"));

                        TableColumn<Result, String> RatingColumn = new TableColumn<>("Rating");
                        RatingColumn.setMinWidth(200);
                        RatingColumn.setCellValueFactory(new PropertyValueFactory<>("Rating"));

                        TableColumn actionCol = new TableColumn("Place Order");
                        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

                        Callback<TableColumn<Result, String>, TableCell<Result, String>> cellFactory
                                = //
                                new Callback<TableColumn<Result, String>, TableCell<Result, String>>() {

                            @Override
                            public TableCell call(final TableColumn<Result, String> param) {
                                final TableCell<Result, String> cell = new TableCell<Result, String>() {

                                    final Button btn = new Button("Order");

                                    @Override
                                    public void updateItem(String item, boolean empty) {

                                        super.updateItem(item, empty);
                                        if (empty) {
                                            setGraphic(null);
                                            setText(null);
                                        } else {
                                            btn.setOnAction(event -> {

                                                Result r = getTableView().getItems().get(getIndex());                                                // eikhane sql boshbe

                                                VBox orderScenevb = new VBox();
                                                orderScenevb.setSpacing(20);
                                                orderScenevb.setAlignment(Pos.CENTER);

                                                TextField orderScenecomment = new TextField();
                                                Label givecomment = new Label("Add a comment about this item (optional):");
                                                orderScenevb.getChildren().add(givecomment);
                                                orderScenevb.getChildren().add(orderScenecomment);

                                                TextField orderScenetf = new TextField();
                                                Button amountok = new Button("OK");
                                                Label writequantity = new Label("How many " + r.getItem_Name() + "s do you want?");
                                                orderScenevb.getChildren().add(writequantity);
                                                orderScenevb.getChildren().add(orderScenetf);
                                                orderScenevb.getChildren().add(amountok);
                                                amountok.setOnAction(event2 -> {
                                                    if (orderScenetf.getText() != null) {
                                                        int price = Integer.valueOf(r.getPrice());
                                                        int totalPrice = price * Integer.valueOf(orderScenetf.getText());
                                                        orderScenetf.setDisable(true);
                                                        //orderScenetf.disableProperty();
                                                        orderScenevb.getChildren().remove(amountok);
                                                        String tP = String.valueOf(totalPrice);
                                                        Label tplabel = new Label("Your Total Price is: " + tP);
                                                        orderScenevb.getChildren().add(tplabel);
                                                        Button confirmorder = new Button("Confirm");
                                                        confirmorder.setOnAction(event3 -> {

                                                            String MNSelectedResTHR = r.getRestaurant_Name();
                                                            String MNSelectedLocTHR = r.getLocation_Name();
                                                            String MNSelectedItT = r.getItem_Type();
                                                            String MNSelectedItN = r.getItem_Name();
                                                            int amount = Integer.valueOf(orderScenetf.getText());
                                                            String com = orderScenecomment.getText();

                                                            ConnectionClass conC = new ConnectionClass();
                                                            Connection con = conC.getConnection();
                                                            try {

                                                                CallableStatement ins = con.prepareCall("{?= call SET_BRANCHMENU_ORDER(?,?,?,?,?,?,?)}");

                                                                ins.setString(2, MNSelectedResTHR);
                                                                ins.setString(3, MNSelectedLocTHR);
                                                                ins.setString(4, MNSelectedItT);
                                                                ins.setString(5, MNSelectedItN);
                                                                ins.setString(6, uname);
                                                                ins.setDouble(7, amount);
                                                                ins.setString(8, com);
                                                                ins.registerOutParameter(1, Types.INTEGER);
                                                                ins.execute();
                                                                System.out.println(ins.getInt(1));

                                                                if (ins.getInt(1) != 1) {
                                                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                                                    alert.setTitle("Insert Error");
                                                                    alert.setHeaderText("Insert Error");
                                                                    alert.setContentText("Make sure you've inserted an amount!");
                                                                    alert.showAndWait();
                                                                }

                                                                ins.close();
                                                                con.close();
                                                                conC.closeConnection();

                                                                //System.out.println(uname);
                                                            } catch (SQLException e) {
                                                                e.printStackTrace();
                                                            }
                                                            //INSERT INTO RESTAURANTS_BRANCHES (REST_ID, LOCATION_ID, MANAGER_NAME, ADDRESS, CONTACT_NUMBER, OPEN_TIME, CLOSE_TIME, WEEKLY_HOLIDAY) VALUES (?, ?, ?, ?, ?, ?, ?, ?)    
                                                            primaryStage.setScene(scene);

                                                        });
                                                        orderScenevb.getChildren().add(confirmorder);
                                                        Button editorder = new Button("Edit");
                                                        orderScenevb.getChildren().add(editorder);
                                                        editorder.setOnAction(event4 -> {
                                                            orderScenevb.getChildren().remove(tplabel);
                                                            orderScenevb.getChildren().remove(confirmorder);
                                                            orderScenevb.getChildren().remove(editorder);
                                                            orderScenevb.getChildren().add(amountok);
                                                            orderScenetf.setDisable(false);
                                                        });

                                                    }
                                                });

                                                orderScenevb.setId("CommonBackground");

                                                Scene orderScene = new Scene(orderScenevb, 500, 500);
                                                orderScene.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());

                                                primaryStage.setScene(orderScene);

                                                // eikhane sql boshbe
                                            });
                                            setGraphic(btn);
                                            setText(null);
                                        }
                                    }
                                };
                                return cell;
                            }
                        };

                        actionCol.setCellFactory(cellFactory);

                        table = new TableView<>();
                        table.setItems(results);
                        table.getColumns().addAll(rest_nameColumn, loc_nameColumn, AddressColumn, Item_TypeColumn, Item_NameColumn, PriceColumn, OfferColumn, RatingColumn, actionCol);

                        Button logoutres = new Button("Logout");
                        logoutres.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                primaryStage.setScene(loginScene);
                            }
                        });

                        Button goBackToPlanning = new Button("Back to Planning");
                        goBackToPlanning.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                primaryStage.setScene(scene);
                            }
                        });

                        VBox vbox = new VBox();
                        vbox.getChildren().addAll(table, logoutres, goBackToPlanning);
                        vbox.setAlignment(Pos.CENTER);
                        vbox.setId("vboxBackground");
                        Scene resultScene = new Scene(vbox);

                        resultScene.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());

                        primaryStage.setScene(resultScene);
                        primaryStage.show();
                    }
                });

                root.add(logoutloc, 2, 10);

                primaryStage.setScene(scene);
            }
        });

        ShowRestB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Scene TransHomeRest;

                VBox TransHomeRestVB = new VBox();
                TransHomeRestVB.setId("TransHomeRest");
                TransHomeRestVB.setAlignment(Pos.CENTER);
                TransHomeRestVB.setSpacing(30);

                ChoiceBox THRlocations = new ChoiceBox();
                ConnectionClass conC = new ConnectionClass();
                Connection con = conC.getConnection();
                List THRlistlocs = new ArrayList();
                try {

                    String sql = "SELECT LOCATION_NAME FROM LOCATIONS";
                    PreparedStatement pst = con.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        THRlistlocs.add(rs.getString(1));
                    }
                    pst.close();
                    con.close();
                    conC.closeConnection();

                    //System.out.println(uname);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                Text thrtext = new Text("Select Area!");
                thrtext.setFont(Font.font("Verdana", 30));

                THRlocations.getItems().addAll(THRlistlocs);
                THRlocations.setValue(THRlistlocs.get(0));
                THRlocations.setPrefSize(250, 40);
                THRlocations.setId("NormalButtonsStyle");

                Button THRok = new Button("Select");

                Button THRBack = new Button("Back");
                THRBack.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setScene(HomeScene);
                    }
                });

                TransHomeRestVB.getChildren().addAll(thrtext, THRlocations, THRok, THRBack);
                TransHomeRest = new Scene(TransHomeRestVB, 500, 500);
                TransHomeRest.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());
                //END OF TRANS HOME REST SCENE

                THRok.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String SelectedLocTHR = (String) THRlocations.getValue();

                        Scene RestaurantsRes;
                        GridPane ShowRestGP = new GridPane();
                        ShowRestGP.setVgap(1);
                        ShowRestGP.setHgap(20);

                        Button BackRestaurantsRes = new Button("Back");

                        BackRestaurantsRes.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                primaryStage.setScene(TransHomeRest);

                            }
                        });

                        ConnectionClass conC = new ConnectionClass();
                        Connection con = conC.getConnection();
                        List listofrests = new ArrayList();
                        int no_of_rests = 0;
                        try {

                            String sql = "select r.restaurant_name from restaurants_branches rb join restaurants r using (rest_id) join locations l using (location_id) where l.location_name = ?";
                            PreparedStatement pst = con.prepareStatement(sql);
                            pst.setString(1, SelectedLocTHR);
                            ResultSet rs = pst.executeQuery();
                            while (rs.next()) {
                                listofrests.add(rs.getString(1));
                                no_of_rests++;
                            }
                            pst.close();
                            con.close();
                            conC.closeConnection();
                            System.out.println(no_of_rests);
                            //System.out.println(uname);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        System.out.println(no_of_rests);

                        //System.out.println(listofrests.get(1));
                        for (int i = 0; i < no_of_rests; i++) {
                            Button temp = new Button((String) listofrests.get(i));
                            temp.setPrefSize(500, 90);
                            temp.setId("temp");
                            System.out.println(temp.getText());

                            temp.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {

                                    Scene Restaurantprofile;
                                    VBox RestProfVB = new VBox();
                                    RestProfVB.setAlignment(Pos.CENTER);
                                    RestProfVB.setSpacing(20);

                                    ConnectionClass conC = new ConnectionClass();
                                    Connection con = conC.getConnection();
                                    String rest_name = null;
                                    String manager_name = null;
                                    String address = null;
                                    String contact_no = null;
                                    String open_time = null;
                                    String close_time = null;
                                    String weekly_holiday = null;

                                    try {

                                        String sql = "select r.restaurant_name, rb.manager_name, rb.address, rb.contact_number, rb.open_time, rb.close_time, rb.weekly_holiday from restaurants_branches rb join restaurants r using (rest_id) join locations l using (location_id) where l.location_name = ? and r.restaurant_name = ?";
                                        PreparedStatement pst = con.prepareStatement(sql);
                                        pst.setString(1, SelectedLocTHR);
                                        pst.setString(2, temp.getText());
                                        ResultSet rs = pst.executeQuery();
                                        //String rest_name, manager_name, address, contact_no, open_time, close_time, weekly_holiday;

                                        while (rs.next()) {
                                            rest_name = rs.getString(1);
                                            manager_name = rs.getString(2);
                                            address = rs.getString(3);
                                            contact_no = rs.getString(4);
                                            open_time = rs.getString(5);
                                            close_time = rs.getString(6);
                                            weekly_holiday = rs.getString(7);
                                        }
                                        pst.close();
                                        con.close();
                                        conC.closeConnection();
                                        System.out.println(rest_name + manager_name + address + contact_no + open_time + close_time + weekly_holiday);

                                        //System.out.println(uname);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }

                                    Text restnametext = new Text("Restaurant Name: " + rest_name);
                                    Text mangrnametext = new Text("Manager: " + manager_name);
                                    Text addresstext = new Text("Address in " + SelectedLocTHR + ": " + address);
                                    Text contact_notext = new Text("Contact Number: " + contact_no);
                                    Text opentimetext = new Text(temp.getText() + " opens at " + open_time + " and closes at " + close_time);
                                    Text weekly_holidaytext = new Text("Weekly off days " + weekly_holiday);
                                    Button backtorestlistselect = new Button("Back");

                                    backtorestlistselect.setOnAction(new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent event) {
                                            primaryStage.setScene(TransHomeRest);
                                        }
                                    });

                                    Button showMenu = new Button("Show Menu");
                                    showMenu.setOnAction(new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent event) {
                                            ConnectionClass conC = new ConnectionClass();
                                            Connection con = conC.getConnection();
                                            ObservableList<MenuResults> menu = FXCollections.observableArrayList();
                                            try {
                                                String sql = "SELECT i.item_name, i.item_type, bm.offer, bm.price FROM BRANCH_MENUS bm JOIN RESTAURANTS r using (rest_id) join locations l using (location_id) join menu_items i using (item_id) where r.restaurant_name = ? and l.location_name = ? ";
                                                PreparedStatement pst = con.prepareStatement(sql);
                                                pst.setString(1, temp.getText());
                                                pst.setString(2, SelectedLocTHR);
                                                ResultSet rs = pst.executeQuery();
                                                while (rs.next()) {
                                                    String str1 = rs.getString(1);
                                                    String str2 = rs.getString(2);
                                                    String str3 = rs.getString(3);

                                                    String str4 = Integer.toString(rs.getInt(4));
                                                    menu.add(new MenuResults(str1, str2, str3, str4));
                                                }
                                                pst.close();
                                                con.close();
                                                conC.closeConnection();
                                            } catch (SQLException e) {
                                                e.printStackTrace();
                                            }

                                            TableView<MenuResults> menuTable;

                                            TableColumn<MenuResults, String> Item_NameColumn = new TableColumn<>("Item_Name");
                                            Item_NameColumn.setMinWidth(200);
                                            Item_NameColumn.setCellValueFactory(new PropertyValueFactory<>("Item_Name"));

                                            TableColumn<MenuResults, String> Item_TypeColumn = new TableColumn<>("Item_Type");
                                            Item_TypeColumn.setMinWidth(200);
                                            Item_TypeColumn.setCellValueFactory(new PropertyValueFactory<>("Item_Type"));

                                            TableColumn<MenuResults, String> OfferColumn = new TableColumn<>("Offer");
                                            OfferColumn.setMinWidth(200);
                                            OfferColumn.setCellValueFactory(new PropertyValueFactory<>("Offer"));

                                            TableColumn<MenuResults, String> PriceColumn = new TableColumn<>("Price");
                                            PriceColumn.setMinWidth(200);
                                            PriceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));

                                            TableColumn actionCol = new TableColumn("Place Order");
                                            actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

                                            Callback<TableColumn<MenuResults, String>, TableCell<MenuResults, String>> cellFactory
                                                    = //
                                                    new Callback<TableColumn<MenuResults, String>, TableCell<MenuResults, String>>() {

                                                @Override
                                                public TableCell call(final TableColumn<MenuResults, String> param) {
                                                    final TableCell<MenuResults, String> cell = new TableCell<MenuResults, String>() {

                                                        final Button btn = new Button("Order");

                                                        @Override
                                                        public void updateItem(String item, boolean empty) {

                                                            super.updateItem(item, empty);
                                                            if (empty) {
                                                                setGraphic(null);
                                                                setText(null);
                                                            } else {
                                                                btn.setOnAction(event -> {

                                                                    MenuResults r = getTableView().getItems().get(getIndex());

                                                                    VBox orderScenevb = new VBox();
                                                                    orderScenevb.setSpacing(20);
                                                                    orderScenevb.setAlignment(Pos.CENTER);

                                                                    TextField orderScenecomment = new TextField();
                                                                    Label givecomment = new Label("Add a comment about this item (optional):");
                                                                    orderScenevb.getChildren().add(givecomment);
                                                                    orderScenevb.getChildren().add(orderScenecomment);

                                                                    TextField orderScenetf = new TextField();
                                                                    Button amountok = new Button("OK");
                                                                    Label writequantity = new Label("How many " + r.getItem_Name() + "s do you want?");
                                                                    orderScenevb.getChildren().add(writequantity);
                                                                    orderScenevb.getChildren().add(orderScenetf);
                                                                    orderScenevb.getChildren().add(amountok);
                                                                    amountok.setOnAction(event2 -> {
                                                                        if (orderScenetf.getText() != null) {
                                                                            int price = Integer.valueOf(r.getPrice());
                                                                            int totalPrice = price * Integer.valueOf(orderScenetf.getText());
                                                                            orderScenetf.setDisable(true);
                                                                            //orderScenetf.disableProperty();
                                                                            orderScenevb.getChildren().remove(amountok);
                                                                            String tP = String.valueOf(totalPrice);
                                                                            Label tplabel = new Label("Your Total Price is: " + tP);
                                                                            orderScenevb.getChildren().add(tplabel);
                                                                            Button confirmorder = new Button("Confirm");
                                                                            confirmorder.setOnAction(event3 -> {

                                                                                String MNSelectedResTHR = temp.getText();
                                                                                String MNSelectedLocTHR = (String) THRlocations.getValue();
                                                                                String MNSelectedItT = r.getItem_Type();
                                                                                String MNSelectedItN = r.getItem_Name();
                                                                                int amount = Integer.valueOf(orderScenetf.getText());
                                                                                String com = orderScenecomment.getText();

                                                                                ConnectionClass conC = new ConnectionClass();
                                                                                Connection con = conC.getConnection();
                                                                                try {

                                                                                    CallableStatement ins = con.prepareCall("{?= call SET_BRANCHMENU_ORDER(?,?,?,?,?,?,?)}");

                                                                                    ins.setString(2, MNSelectedResTHR);
                                                                                    ins.setString(3, MNSelectedLocTHR);
                                                                                    ins.setString(4, MNSelectedItT);
                                                                                    ins.setString(5, MNSelectedItN);
                                                                                    ins.setString(6, uname);
                                                                                    ins.setDouble(7, amount);
                                                                                    ins.setString(8, com);
                                                                                    ins.registerOutParameter(1, Types.INTEGER);
                                                                                    ins.execute();
                                                                                    System.out.println(ins.getInt(1));

                                                                                    if (ins.getInt(1) != 1) {
                                                                                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                                                                        alert.setTitle("Insert Error");
                                                                                        alert.setHeaderText("Insert Error");
                                                                                        alert.setContentText("Make sure you've inserted an amount!");
                                                                                        alert.showAndWait();
                                                                                    }

                                                                                    ins.close();
                                                                                    con.close();
                                                                                    conC.closeConnection();

                                                                                    //System.out.println(uname);
                                                                                } catch (SQLException e) {
                                                                                    e.printStackTrace();
                                                                                }
                                                                                //INSERT INTO RESTAURANTS_BRANCHES (REST_ID, LOCATION_ID, MANAGER_NAME, ADDRESS, CONTACT_NUMBER, OPEN_TIME, CLOSE_TIME, WEEKLY_HOLIDAY) VALUES (?, ?, ?, ?, ?, ?, ?, ?)    
                                                                                primaryStage.setScene(HomeScene);

                                                                            });
                                                                            orderScenevb.getChildren().add(confirmorder);
                                                                            Button editorder = new Button("Edit");
                                                                            orderScenevb.getChildren().add(editorder);
                                                                            editorder.setOnAction(event4 -> {
                                                                                orderScenevb.getChildren().remove(tplabel);
                                                                                orderScenevb.getChildren().remove(confirmorder);
                                                                                orderScenevb.getChildren().remove(editorder);
                                                                                orderScenevb.getChildren().add(amountok);
                                                                                orderScenetf.setDisable(false);
                                                                            });

                                                                        }
                                                                    });

                                                                    orderScenevb.setId("CommonBackground");

                                                                    Scene orderScene = new Scene(orderScenevb, 500, 500);
                                                                    orderScene.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());

                                                                    primaryStage.setScene(orderScene);

                                                                    // eikhane sql boshbe
                                                                });
                                                                setGraphic(btn);
                                                                setText(null);
                                                            }
                                                        }
                                                    };
                                                    return cell;
                                                }
                                            };

                                            actionCol.setCellFactory(cellFactory);

                                            menuTable = new TableView<>();
                                            menuTable.setItems(menu);
                                            menuTable.getColumns().addAll(Item_NameColumn, Item_TypeColumn, OfferColumn, PriceColumn, actionCol);
                                            Button backtoShowRest = new Button("Back");
                                            backtoShowRest.setOnAction(new EventHandler<ActionEvent>() {
                                                @Override
                                                public void handle(ActionEvent event) {
                                                    primaryStage.setScene(TransHomeRest);
                                                }
                                            });
                                            VBox vbox = new VBox();
                                            vbox.setAlignment(Pos.CENTER);

                                            vbox.getChildren().addAll(menuTable, backtoShowRest);

                                            Scene menuScene = new Scene(vbox);
                                            menuScene.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());
                                            primaryStage.setScene(menuScene);
                                        }
                                    });

                                    RestProfVB.getChildren().addAll(restnametext, mangrnametext, addresstext, contact_notext, opentimetext, weekly_holidaytext, showMenu, backtorestlistselect);
                                    RestProfVB.setId("RestProfVB");
                                    Restaurantprofile = new Scene(RestProfVB, 500, 500);

                                    Restaurantprofile.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());
                                    primaryStage.setScene(Restaurantprofile);

                                }
                            });

                            ShowRestGP.add(temp, 0, i + 30);
                        }
                        ShowRestGP.add(BackRestaurantsRes, 0, 0);
                        ShowRestGP.setId("ShowRestBackground");
                        ScrollPane ShowRestSp = new ScrollPane();
                        ShowRestSp.setContent(ShowRestGP);
                        ShowRestSp.setId("ShowRestBackground");
                        RestaurantsRes = new Scene(ShowRestSp, 500, 500);
                        RestaurantsRes.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());
                        primaryStage.setScene(RestaurantsRes);
                    }
                });
                primaryStage.setScene(TransHomeRest);
            }
        });

        RateBranch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Scene bratscene;
                VBox RateBRvb = new VBox();
                RateBRvb.setAlignment(Pos.CENTER);
                RateBRvb.setSpacing(20);
                RateBRvb.setId("CommonBackground");
                ChoiceBox RBRlocations = new ChoiceBox();
                ConnectionClass conC = new ConnectionClass();
                Connection con = conC.getConnection();
                List RBRlistlocs = new ArrayList();
                try {

                    String sql = "SELECT LOCATION_NAME FROM LOCATIONS";
                    PreparedStatement pst = con.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        RBRlistlocs.add(rs.getString(1));
                    }
                    pst.close();
                    con.close();
                    conC.closeConnection();

                    //System.out.println(uname);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                RBRlocations.getItems().addAll(RBRlistlocs);
                RBRlocations.setValue(RBRlistlocs.get(0));
                RBRlocations.setPrefSize(250, 40);
                RBRlocations.setId("NormalButtonsStyle");

                Button backratebranch_home = new Button("Back");
                backratebranch_home.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setScene(HomeScene);
                    }
                });

                Button logoutratebranch = new Button("Logout");
                logoutratebranch.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setScene(loginScene);
                    }
                });

                Button RBRok = new Button("Select");
                //RBRlocations
                //RBRok
                RateBRvb.getChildren().addAll(RBRlocations, RBRok, backratebranch_home, logoutratebranch);
                bratscene = new Scene(RateBRvb, 500, 500);
                bratscene.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());
                RBRok.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String RBSelectedLocTHR = (String) RBRlocations.getValue();

                        Scene RBRestaurantsRes;
                        GridPane RBShowRestGP = new GridPane();
                        RBShowRestGP.setId("CommonBackground");
                        RBShowRestGP.setVgap(1);
                        RBShowRestGP.setHgap(20);

                        Button backrest_bratscene = new Button("Back");
                        RBShowRestGP.add(backrest_bratscene, 0, 0);
                        backrest_bratscene.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                primaryStage.setScene(bratscene);
                            }
                        });

                        ConnectionClass conC = new ConnectionClass();
                        Connection con = conC.getConnection();
                        List rblistofrests = new ArrayList();
                        int rbno_of_rests = 0;
                        try {

                            String sql = "select r.restaurant_name from restaurants_branches rb join restaurants r using (rest_id) join locations l using (location_id) where l.location_name = ?";
                            PreparedStatement pst = con.prepareStatement(sql);
                            pst.setString(1, RBSelectedLocTHR);
                            ResultSet rs = pst.executeQuery();
                            while (rs.next()) {
                                rblistofrests.add(rs.getString(1));
                                rbno_of_rests++;
                            }
                            pst.close();
                            con.close();
                            conC.closeConnection();
                            System.out.println(rbno_of_rests);
                            //System.out.println(uname);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        System.out.println(rbno_of_rests);

                        //System.out.println(listofrests.get(1));
                        for (int i = 0; i < rbno_of_rests; i++) {
                            Button rbtemp = new Button((String) rblistofrests.get(i));
                            rbtemp.setPrefSize(500, 90);
                            rbtemp.setId("temp");
                            System.out.println(rbtemp.getText());

                            rbtemp.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    //System.out.println(rbtemp.getText());
                                    String RBSelectedResTHR = rbtemp.getText();
                                    //System.out.println(RBSelectedResTHR);
                                    Scene rbscene;

                                    VBox rbscenevb = new VBox();

                                    rbscenevb.setAlignment(Pos.CENTER);
                                    rbscenevb.setSpacing(20);
                                    rbscene = new Scene(rbscenevb, 500, 500);
                                    rbscene.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());

                                    TextField brating = new TextField();
                                    brating.setPrefSize(250, 40);
                                    brating.setPromptText("Enter Rating");
                                    brating.setId("NormalButtonsStyle");
                                    //brating

                                    Button home_rbscene = new Button("Go to Home");

                                    home_rbscene.setOnAction(new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent event) {
                                            primaryStage.setScene(HomeScene);
                                        }
                                    });

                                    TextField brc = new TextField();
                                    brc.setPrefSize(250, 40);
                                    brc.setPromptText("Enter Comment");
                                    brc.setId("NormalButtonsStyle");
                                    //brc

                                    Button rbok = new Button("Enter");
                                    //rbok
                                    rbscenevb.getChildren().addAll(brating, brc, rbok, home_rbscene);
                                    rbok.setOnAction(new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent event) {
                                            ConnectionClass conC = new ConnectionClass();
                                            Connection con = conC.getConnection();
                                            try {

                                                CallableStatement ins = con.prepareCall("{?= call SET_BRANCH_RATING(?,?,?,?,?)}");

                                                ins.setString(2, RBSelectedResTHR);
                                                ins.setString(3, RBSelectedLocTHR);
                                                ins.setString(4, usernameli.getText());
                                                ins.setDouble(5, Double.parseDouble(brating.getText()));
                                                ins.setString(6, brc.getText());
                                                ins.registerOutParameter(1, Types.INTEGER);
                                                ins.execute();
                                                System.out.println(ins.getInt(1));

                                                if (ins.getInt(1) != 1) {
                                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                                    alert.setTitle("Insert Error");
                                                    alert.setHeaderText("Insert Error");
                                                    alert.setContentText("Make sure you've provided a rating!");
                                                    alert.showAndWait();
                                                }

                                                ins.close();
                                                con.close();
                                                conC.closeConnection();

                                                //System.out.println(uname);
                                            } catch (SQLException e) {
                                                e.printStackTrace();
                                            }
                                            //INSERT INTO RESTAURANTS_BRANCHES (REST_ID, LOCATION_ID, MANAGER_NAME, ADDRESS, CONTACT_NUMBER, OPEN_TIME, CLOSE_TIME, WEEKLY_HOLIDAY) VALUES (?, ?, ?, ?, ?, ?, ?, ?)    
                                            primaryStage.setScene(HomeScene);

                                        }
                                    });

                                    primaryStage.setScene(rbscene);

                                }
                            });

                            RBShowRestGP.add(rbtemp, 0, i + 30);
                        }
                        ScrollPane RBShowRestSp = new ScrollPane();
                        RBShowRestSp.setContent(RBShowRestGP);
                        RBRestaurantsRes = new Scene(RBShowRestSp, 500, 500);
                        RBRestaurantsRes.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());
                        primaryStage.setScene(RBRestaurantsRes);
                    }
                });
                primaryStage.setScene(bratscene);
            }
        });

        RateMeal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Scene menratscene;

                GridPane RateMN = new GridPane();
                RateMN.setVgap(20);
                RateMN.setHgap(20);
                RateMN.setId("CommonBackground");
                ChoiceBox MNRlocations = new ChoiceBox();
                MNRlocations.setId("NormalButtonsStyle");

                ConnectionClass conC = new ConnectionClass();
                Connection con = conC.getConnection();
                List MNRlistlocs = new ArrayList();
                try {

                    String sql = "SELECT LOCATION_NAME FROM LOCATIONS";
                    PreparedStatement pst = con.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        MNRlistlocs.add(rs.getString(1));
                    }
                    pst.close();
                    con.close();
                    conC.closeConnection();

                    //System.out.println(uname);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                MNRlocations.getItems().addAll(MNRlistlocs);
                MNRlocations.setValue(MNRlistlocs.get(0));
                MNRlocations.setPrefSize(250, 40);

                Button MNRok = new Button("Select");
                RateMN.add(MNRlocations, 2, 4);
                RateMN.add(MNRok, 5, 4);
                menratscene = new Scene(RateMN, 500, 500);
                menratscene.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());

                Button home_abcd = new Button("Go to Home!");
                RateMN.add(home_abcd, 5, 5);
                home_abcd.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setScene(HomeScene);
                    }
                });

                MNRok.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String MNSelectedLocTHR = (String) MNRlocations.getValue();

                        Scene MNRestaurantsRes;
                        GridPane MNShowRestGP = new GridPane();
                        MNShowRestGP.setId("CommonBackground");
                        MNShowRestGP.setVgap(1);
                        MNShowRestGP.setHgap(20);

                        Button home_qwer = new Button("Go to Home!");

                        home_qwer.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                primaryStage.setScene(HomeScene);
                            }
                        });

                        MNShowRestGP.add(home_qwer, 0, 0);

                        ConnectionClass conC = new ConnectionClass();
                        Connection con = conC.getConnection();
                        List mnlistofrests = new ArrayList();
                        int mnno_of_rests = 0;
                        try {

                            String sql = "select distinct(r.restaurant_name) from branch_menus rb join restaurants r using (rest_id) join locations l using (location_id) where l.location_name = ?";
                            PreparedStatement pst = con.prepareStatement(sql);
                            pst.setString(1, MNSelectedLocTHR);
                            ResultSet rs = pst.executeQuery();
                            while (rs.next()) {
                                mnlistofrests.add(rs.getString(1));
                                mnno_of_rests++;
                            }
                            pst.close();
                            con.close();
                            conC.closeConnection();
                            System.out.println(mnno_of_rests);
                            //System.out.println(uname);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        System.out.println(mnno_of_rests);

                        //System.out.println(listofrests.get(1));
                        for (int i = 0; i < mnno_of_rests; i++) {
                            Button mntemp = new Button((String) mnlistofrests.get(i));
                            mntemp.setPrefSize(400, 90);
                            mntemp.setId("temp");
                            System.out.println(mntemp.getText());

                            mntemp.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    //System.out.println(rbtemp.getText());
                                    String MNSelectedResTHR = mntemp.getText();
                                    System.out.println(MNSelectedResTHR);
                                    Scene s;

                                    GridPane RMN = new GridPane();
                                    RMN.setVgap(20);
                                    RMN.setHgap(20);
                                    RMN.setId("CommonBackground");
                                    ChoiceBox MNRit = new ChoiceBox();
                                    MNRit.setId("NormalButtonsStyle");

                                    Button home_rmn = new Button("Home!");
                                    home_rmn.setOnAction(new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent event) {
                                            primaryStage.setScene(HomeScene);
                                        }
                                    });
                                    ConnectionClass conC = new ConnectionClass();
                                    Connection con = conC.getConnection();
                                    List MNRlistits = new ArrayList();

                                    //System.out.println(MNSelectedLocTHR);
                                    //System.out.println(MNSelectedResTHR);
                                    try {

                                        String sql = "select DISTINCT (i.ITEM_TYPE) from BRANCH_MENUS bm join RESTAURANTS r on (bm.REST_ID = r.REST_ID) join LOCATIONS l on (l.LOCATION_ID = bm.LOCATION_ID) join MENU_ITEMS i on (i.ITEM_ID = bm.ITEM_ID) where r.RESTAURANT_NAME = ? AND l.LOCATION_NAME = ?";
                                        PreparedStatement pst = con.prepareStatement(sql);
                                        pst.setString(1, MNSelectedResTHR);
                                        pst.setString(2, MNSelectedLocTHR);

                                        ResultSet rs = pst.executeQuery();
                                        while (rs.next()) {
                                            MNRlistits.add(rs.getString(1));
                                        }
                                        pst.close();
                                        con.close();
                                        conC.closeConnection();

                                        //System.out.println(uname);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    MNRit.getItems().addAll(MNRlistits);
                                    MNRit.setValue(MNRlistits.get(0));
                                    MNRit.setPrefSize(250, 40);

                                    Button ok = new Button("Select");
                                    RMN.add(MNRit, 2, 4);
                                    RMN.add(ok, 5, 4);
                                    RMN.add(home_rmn, 5, 5);

                                    ok.setOnAction(new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent event) {
                                            String MNSelectedItT = (String) MNRit.getValue();
                                            //System.out.println(MNSelectedItT);

                                            Scene mnscene;
                                            GridPane mnroot = new GridPane();
                                            mnroot.setVgap(20);
                                            mnroot.setHgap(20);
                                            mnroot.setAlignment(Pos.CENTER);
                                            mnroot.setId("CommonBackground");
                                            mnscene = new Scene(mnroot, 500, 500);
                                            mnscene.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());

                                            mnscene.setFill(Color.BLACK);

                                            ChoiceBox mnRit = new ChoiceBox();
                                            mnRit.setId("NormalButtonsStyle");

                                            Button home_yu = new Button("Home!");
                                            home_yu.setOnAction(new EventHandler<ActionEvent>() {
                                                @Override
                                                public void handle(ActionEvent event) {
                                                    primaryStage.setScene(HomeScene);
                                                }
                                            });
                                            ConnectionClass conC = new ConnectionClass();
                                            Connection con = conC.getConnection();
                                            List mnRlistits = new ArrayList();

                                            //System.out.println(MNSelectedLocTHR);
                                            //System.out.println(MNSelectedResTHR);
                                            try {

                                                String sql = "select DISTINCT (i.ITEM_NAME) from BRANCH_MENUS bm join RESTAURANTS r on (bm.REST_ID = r.REST_ID) join LOCATIONS l on (l.LOCATION_ID = bm.LOCATION_ID) join MENU_ITEMS i on (i.ITEM_ID = bm.ITEM_ID) where r.RESTAURANT_NAME = ? AND l.LOCATION_NAME = ? and i.ITEM_TYPE = ?";
                                                PreparedStatement pst = con.prepareStatement(sql);
                                                pst.setString(1, MNSelectedResTHR);
                                                pst.setString(2, MNSelectedLocTHR);
                                                pst.setString(3, MNSelectedItT);

                                                ResultSet rs = pst.executeQuery();
                                                while (rs.next()) {
                                                    mnRlistits.add(rs.getString(1));
                                                }
                                                pst.close();
                                                con.close();
                                                conC.closeConnection();

                                                //System.out.println(uname);
                                            } catch (SQLException e) {
                                                e.printStackTrace();
                                            }
                                            mnRit.getItems().addAll(mnRlistits);
                                            mnRit.setValue(mnRlistits.get(0));
                                            mnRit.setPrefSize(250, 40);
                                            mnroot.add(mnRit, 1, 1);

                                            TextField mnating = new TextField();
                                            mnating.setPrefSize(250, 40);
                                            mnating.setPromptText("Enter Rating");
                                            mnroot.add(mnating, 1, 3);

                                            TextField mnc = new TextField();
                                            mnc.setPrefSize(250, 40);
                                            mnc.setPromptText("Enter Comment");
                                            mnroot.add(mnc, 1, 5);

                                            Button mnok = new Button("Enter");
                                            mnroot.add(mnok, 5, 4);
                                            mnroot.add(home_yu, 5, 5);
                                            mnok.setOnAction(new EventHandler<ActionEvent>() {
                                                @Override
                                                public void handle(ActionEvent event) {
                                                    String MNSelectedItN = (String) mnRit.getValue();
                                                    //System.out.println(MNSelectedItN);
                                                    ConnectionClass conC = new ConnectionClass();
                                                    Connection con = conC.getConnection();
                                                    try {

                                                        CallableStatement ins = con.prepareCall("{?= call SET_BRANCHMENU_RATING(?,?,?,?,?,?,?)}");

                                                        ins.setString(2, MNSelectedResTHR);
                                                        ins.setString(3, MNSelectedLocTHR);
                                                        ins.setString(4, MNSelectedItT);
                                                        ins.setString(5, MNSelectedItN);
                                                        ins.setString(6, uname);
                                                        ins.setDouble(7, Double.parseDouble(mnating.getText()));
                                                        ins.setString(8, mnc.getText());
                                                        ins.registerOutParameter(1, Types.INTEGER);
                                                        ins.execute();
                                                        System.out.println(ins.getInt(1));

                                                        if (ins.getInt(1) != 1) {
                                                            Alert alert = new Alert(Alert.AlertType.ERROR);
                                                            alert.setTitle("Insert Error");
                                                            alert.setHeaderText("Insert Error");
                                                            alert.setContentText("Make sure you've provided a rating!");
                                                            alert.showAndWait();
                                                        }

                                                        ins.close();
                                                        con.close();
                                                        conC.closeConnection();

                                                        //System.out.println(uname);
                                                    } catch (SQLException e) {
                                                        e.printStackTrace();
                                                    }
                                                    //INSERT INTO RESTAURANTS_BRANCHES (REST_ID, LOCATION_ID, MANAGER_NAME, ADDRESS, CONTACT_NUMBER, OPEN_TIME, CLOSE_TIME, WEEKLY_HOLIDAY) VALUES (?, ?, ?, ?, ?, ?, ?, ?)    
                                                    primaryStage.setScene(HomeScene);

                                                }
                                            });

                                            primaryStage.setScene(mnscene);

                                        }
                                    });

                                    s = new Scene(RMN, 500, 500);
                                    s.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());
                                    primaryStage.setScene(s);

                                }
                            });

                            MNShowRestGP.add(mntemp, 0, i + 30);
                        }
                        ScrollPane MNShowRestSp = new ScrollPane();
                        MNShowRestSp.setContent(MNShowRestGP);
                        MNRestaurantsRes = new Scene(MNShowRestSp, 400, 400);
                        MNRestaurantsRes.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());
                        primaryStage.setScene(MNRestaurantsRes);
                    }
                });
                primaryStage.setScene(menratscene);
            }
        });

        ShowOffer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ConnectionClass conC = new ConnectionClass();
                Connection con = conC.getConnection();
                ObservableList<Result> offersos = FXCollections.observableArrayList();
                try {
                    String sql = "select r.RESTAURANT_NAME, l.Location_name, rb.ADDRESS, i.ITEM_TYPE, i.ITEM_NAME, bm.PRICE, bm.OFFER, GET_BRANCHMENU_AVGRATING(r.RESTAURANT_NAME, l.LOCATION_NAME, i.ITEM_ID) from BRANCH_MENUS bm join RESTAURANTS r on (bm.REST_ID = r.REST_ID) join LOCATIONS l on (l.LOCATION_ID = bm.LOCATION_ID) join MENU_ITEMS i on (i.ITEM_ID = bm.ITEM_ID) join RESTAURANTS_BRANCHES rb on (bm.REST_ID = rb.REST_ID and bm.LOCATION_ID = rb.LOCATION_ID) WHERE bm.OFFER <> 'NO OFFER' and bm.OFFER is not null";
                    PreparedStatement pst = con.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        String str1 = rs.getString(1);
                        String str2 = rs.getString(2);
                        String str3 = rs.getString(3);
                        String str4 = rs.getString(4);
                        String str5 = rs.getString(5);
                        String str6 = Integer.toString(rs.getInt(6));
                        String str7 = rs.getString(7);
                        String str8 = Double.toString(rs.getDouble(8));
                        offersos.add(new Result(str1, str2, str3, str4, str5, str6, str7, str8));
                    }
                    pst.close();
                    con.close();
                    conC.closeConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                TableView<Result> Offertable;
                //primaryStage.setTitle("Offer Results");

                TableColumn<Result, String> rest_nameColumn = new TableColumn<>("Restaurant_Name");
                rest_nameColumn.setMinWidth(200);
                rest_nameColumn.setCellValueFactory(new PropertyValueFactory<>("Restaurant_Name"));

                TableColumn<Result, String> loc_nameColumn = new TableColumn<>("Location_Name");
                loc_nameColumn.setMinWidth(200);
                loc_nameColumn.setCellValueFactory(new PropertyValueFactory<>("Location_Name"));

                TableColumn<Result, String> AddressColumn = new TableColumn<>("Address");
                AddressColumn.setMinWidth(200);
                AddressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));

                TableColumn<Result, String> Item_TypeColumn = new TableColumn<>("Item_Type");
                Item_TypeColumn.setMinWidth(200);
                Item_TypeColumn.setCellValueFactory(new PropertyValueFactory<>("Item_Type"));

                TableColumn<Result, String> Item_NameColumn = new TableColumn<>("Item_Name");
                Item_NameColumn.setMinWidth(200);
                Item_NameColumn.setCellValueFactory(new PropertyValueFactory<>("Item_Name"));

                TableColumn<Result, String> PriceColumn = new TableColumn<>("Price");
                PriceColumn.setMinWidth(100);
                PriceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));

                TableColumn<Result, String> OfferColumn = new TableColumn<>("Offer");
                OfferColumn.setMinWidth(200);
                OfferColumn.setCellValueFactory(new PropertyValueFactory<>("Offer"));

                TableColumn<Result, String> RatingColumn = new TableColumn<>("Rating");
                RatingColumn.setMinWidth(200);
                RatingColumn.setCellValueFactory(new PropertyValueFactory<>("Rating"));

                TableColumn actionCol = new TableColumn("Place Order");
                actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

                Callback<TableColumn<Result, String>, TableCell<Result, String>> cellFactory
                        = //
                        new Callback<TableColumn<Result, String>, TableCell<Result, String>>() {

                    @Override
                    public TableCell call(final TableColumn<Result, String> param) {
                        final TableCell<Result, String> cell = new TableCell<Result, String>() {

                            final Button btn = new Button("Order");

                            @Override
                            public void updateItem(String item, boolean empty) {

                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {

                                        Result r = getTableView().getItems().get(getIndex());                                                // eikhane sql boshbe

                                        VBox orderScenevb = new VBox();
                                        orderScenevb.setSpacing(20);
                                        orderScenevb.setAlignment(Pos.CENTER);

                                        TextField orderScenecomment = new TextField();
                                        Label givecomment = new Label("Add a comment about this item (optional):");
                                        orderScenevb.getChildren().add(givecomment);
                                        orderScenevb.getChildren().add(orderScenecomment);

                                        TextField orderScenetf = new TextField();
                                        Button amountok = new Button("OK");
                                        Label writequantity = new Label("How many " + r.getItem_Name() + "s do you want?");
                                        orderScenevb.getChildren().add(writequantity);
                                        orderScenevb.getChildren().add(orderScenetf);
                                        orderScenevb.getChildren().add(amountok);
                                        amountok.setOnAction(event2 -> {
                                            if (orderScenetf.getText() != null) {
                                                int price = Integer.valueOf(r.getPrice());
                                                int totalPrice = price * Integer.valueOf(orderScenetf.getText());
                                                orderScenetf.setDisable(true);
                                                //orderScenetf.disableProperty();
                                                orderScenevb.getChildren().remove(amountok);
                                                String tP = String.valueOf(totalPrice);
                                                Label tplabel = new Label("Your Total Price is: " + tP);
                                                orderScenevb.getChildren().add(tplabel);
                                                Button confirmorder = new Button("Confirm");
                                                confirmorder.setOnAction(event3 -> {

                                                    String MNSelectedResTHR = r.getRestaurant_Name();
                                                    String MNSelectedLocTHR = r.getLocation_Name();
                                                    String MNSelectedItT = r.getItem_Type();
                                                    String MNSelectedItN = r.getItem_Name();
                                                    int amount = Integer.valueOf(orderScenetf.getText());
                                                    String com = orderScenecomment.getText();

                                                    ConnectionClass conC = new ConnectionClass();
                                                    Connection con = conC.getConnection();
                                                    try {

                                                        CallableStatement ins = con.prepareCall("{?= call SET_BRANCHMENU_ORDER(?,?,?,?,?,?,?)}");

                                                        ins.setString(2, MNSelectedResTHR);
                                                        ins.setString(3, MNSelectedLocTHR);
                                                        ins.setString(4, MNSelectedItT);
                                                        ins.setString(5, MNSelectedItN);
                                                        ins.setString(6, uname);
                                                        ins.setDouble(7, amount);
                                                        ins.setString(8, com);
                                                        ins.registerOutParameter(1, Types.INTEGER);
                                                        ins.execute();
                                                        System.out.println(ins.getInt(1));

                                                        if (ins.getInt(1) != 1) {
                                                            Alert alert = new Alert(Alert.AlertType.ERROR);
                                                            alert.setTitle("Insert Error");
                                                            alert.setHeaderText("Insert Error");
                                                            alert.setContentText("Make sure you've provided an amount!");
                                                            alert.showAndWait();
                                                        }

                                                        ins.close();
                                                        con.close();
                                                        conC.closeConnection();

                                                        //System.out.println(uname);
                                                    } catch (SQLException e) {
                                                        e.printStackTrace();
                                                    }
                                                    //INSERT INTO RESTAURANTS_BRANCHES (REST_ID, LOCATION_ID, MANAGER_NAME, ADDRESS, CONTACT_NUMBER, OPEN_TIME, CLOSE_TIME, WEEKLY_HOLIDAY) VALUES (?, ?, ?, ?, ?, ?, ?, ?)    
                                                    primaryStage.setScene(HomeScene);

                                                });
                                                orderScenevb.getChildren().add(confirmorder);
                                                Button editorder = new Button("Edit");
                                                orderScenevb.getChildren().add(editorder);
                                                editorder.setOnAction(event4 -> {
                                                    orderScenevb.getChildren().remove(tplabel);
                                                    orderScenevb.getChildren().remove(confirmorder);
                                                    orderScenevb.getChildren().remove(editorder);
                                                    orderScenevb.getChildren().add(amountok);
                                                    orderScenetf.setDisable(false);
                                                });

                                            }
                                        });

                                        orderScenevb.setId("CommonBackground");

                                        Scene orderScene = new Scene(orderScenevb, 500, 500);
                                        orderScene.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());

                                        primaryStage.setScene(orderScene);

                                        // eikhane sql boshbe
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

                actionCol.setCellFactory(cellFactory);

                Offertable = new TableView<>();
                Offertable.setItems(offersos);
                Offertable.getColumns().addAll(rest_nameColumn, loc_nameColumn, AddressColumn, Item_TypeColumn, Item_NameColumn, PriceColumn, OfferColumn, RatingColumn, actionCol);

                Button goBack_ShowOffer_home = new Button("Back");
                goBack_ShowOffer_home.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setScene(HomeScene);
                    }
                });

                VBox offervbox = new VBox();
                offervbox.getChildren().addAll(goBack_ShowOffer_home, Offertable);
                offervbox.setAlignment(Pos.CENTER);
                offervbox.setId("offervboxbackground");
                Scene offerScene = new Scene(offervbox, 500, 500);
                offerScene.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());

                primaryStage.setScene(offerScene);
            }
        });

        ShowOrder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ConnectionClass conC = new ConnectionClass();
                Connection con = conC.getConnection();
                ObservableList<UserOrder> offersos = FXCollections.observableArrayList();
                try {
                    String sql = "SELECT o.ORDER_ID, r.RESTAURANT_NAME, l.LOCATION_NAME, rb.ADDRESS, rb.CONTACT_NUMBER, i.ITEM_TYPE, i.ITEM_NAME, o.AMOUNT, o.ORDER_DATE, o.order_status from USERS_MENU_ORDER o join USERS u on (o.USER_ID = u.USER_ID) join MENU_ITEMS i on (i.ITEM_ID = o.ITEM_ID) join RESTAURANTS_BRANCHES rb on (o.REST_ID = rb.REST_ID and o.LOCATION_ID = rb.LOCATION_ID) join RESTAURANTS r on (o.REST_ID = r.REST_ID) join LOCATIONS l on (o.LOCATION_ID = l.LOCATION_ID) WHERE u.USERNAME= ? and o.order_status <> 'Paid'";

                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, uname);

                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        String str1 = Integer.toString(rs.getInt(1));
                        String str2 = rs.getString(2);
                        String str3 = rs.getString(3);
                        String str4 = rs.getString(4);
                        String str5 = rs.getString(5);
                        String str6 = rs.getString(6);
                        String str7 = rs.getString(7);
                        String str8 = Integer.toString(rs.getInt(8));

                        String str9 = rs.getString(9);

                        String str10 = rs.getString(10);

                        offersos.add(new UserOrder(str1, str2, str3, str4, str5, str6, str7, str8, str9, str10));
                    }
                    pst.close();
                    con.close();
                    conC.closeConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                TableView<UserOrder> Offertable;

                TableColumn<UserOrder, String> order_idColumn = new TableColumn<>("ORDER_ID");
                order_idColumn.setMinWidth(200);
                order_idColumn.setCellValueFactory(new PropertyValueFactory<>("ORDER_ID"));

                TableColumn<UserOrder, String> rest_nameColumn = new TableColumn<>("Restaurant_Name");
                rest_nameColumn.setMinWidth(200);
                rest_nameColumn.setCellValueFactory(new PropertyValueFactory<>("Restaurant_Name"));

                TableColumn<UserOrder, String> loc_nameColumn = new TableColumn<>("Location_Name");
                loc_nameColumn.setMinWidth(200);
                loc_nameColumn.setCellValueFactory(new PropertyValueFactory<>("Location_Name"));

                TableColumn<UserOrder, String> AddressColumn = new TableColumn<>("Address");
                AddressColumn.setMinWidth(200);
                AddressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));

                TableColumn<UserOrder, String> Item_TypeColumn = new TableColumn<>("Contact_Number");
                Item_TypeColumn.setMinWidth(200);
                Item_TypeColumn.setCellValueFactory(new PropertyValueFactory<>("Contact_Number"));

                TableColumn<UserOrder, String> Item_NameColumn = new TableColumn<>("ITEM_TYPE");
                Item_NameColumn.setMinWidth(200);
                Item_NameColumn.setCellValueFactory(new PropertyValueFactory<>("ITEM_TYPE"));

                TableColumn<UserOrder, String> PriceColumn = new TableColumn<>("ITEM_NAME");
                PriceColumn.setMinWidth(100);
                PriceColumn.setCellValueFactory(new PropertyValueFactory<>("ITEM_NAME"));

                TableColumn<UserOrder, String> OfferColumn = new TableColumn<>("AMOUNT");
                OfferColumn.setMinWidth(200);
                OfferColumn.setCellValueFactory(new PropertyValueFactory<>("AMOUNT"));

                TableColumn<UserOrder, String> RatingColumn = new TableColumn<>("ORDER_DATE");
                RatingColumn.setMinWidth(200);
                RatingColumn.setCellValueFactory(new PropertyValueFactory<>("ORDER_DATE"));

                TableColumn<UserOrder, String> StatusColumn = new TableColumn<>("ORDER_STATUS");
                StatusColumn.setMinWidth(200);
                StatusColumn.setCellValueFactory(new PropertyValueFactory<>("ORDER_STATUS"));

                TableColumn actionCol = new TableColumn("Receival");
                actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

                Callback<TableColumn<UserOrder, String>, TableCell<UserOrder, String>> cellFactory
                        = //
                        new Callback<TableColumn<UserOrder, String>, TableCell<UserOrder, String>>() {

                    @Override
                    public TableCell call(final TableColumn<UserOrder, String> param) {
                        final TableCell<UserOrder, String> cell = new TableCell<UserOrder, String>() {

                            final Button btn = new Button("Received");

                            @Override
                            public void updateItem(String item, boolean empty) {

                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {

                                        UserOrder r = getTableView().getItems().get(getIndex());

                                        int orderId = Integer.parseInt(r.getORDER_ID());

                                        ConnectionClass conC = new ConnectionClass();
                                        Connection con = conC.getConnection();
                                        try {

                                            CallableStatement ins = con.prepareCall("{?= call RECEIVE_ORDER(?)}");

                                            ins.setInt(2, orderId);

                                            ins.registerOutParameter(1, Types.INTEGER);
                                            ins.execute();
                                            System.out.println(ins.getInt(1));

                                            if (ins.getInt(1) != 1) {
                                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                                alert.setTitle("Insert Error");
                                                alert.setHeaderText("Insert Error");
                                                alert.setContentText("Make sure the order exists!");
                                                alert.showAndWait();
                                            }

                                            ins.close();
                                            con.close();
                                            conC.closeConnection();

                                            primaryStage.setScene(HomeScene);

                                            //System.out.println(uname);
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }

                                        primaryStage.setScene(HomeScene);

                                        // eikhane sql boshbe
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

                actionCol.setCellFactory(cellFactory);

                //new button column here
                TableColumn actionCol2 = new TableColumn("Edit Comment");
                actionCol2.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

                Callback<TableColumn<UserOrder, String>, TableCell<UserOrder, String>> cellFactory2
                        = //
                        new Callback<TableColumn<UserOrder, String>, TableCell<UserOrder, String>>() {

                    @Override
                    public TableCell call(final TableColumn<UserOrder, String> param) {
                        final TableCell<UserOrder, String> cell = new TableCell<UserOrder, String>() {

                            final Button btn = new Button("Comment");

                            @Override
                            public void updateItem(String item, boolean empty) {

                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        VBox randomvb = new VBox();
                                        randomvb.setId("CommonBackground");
                                        randomvb.setAlignment(Pos.CENTER);
                                        randomvb.setSpacing(20);
                                        Label l = new Label("Enter your comment here");
                                        TextField randomtf = new TextField();
                                        Button randomok = new Button("Ok");
                                        Button back = new Button("Back");

                                        back.setOnAction(new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent event) {
                                                primaryStage.setScene(HomeScene);
                                            }
                                        });

                                        randomok.setOnAction(event2 -> {
                                            ///SQL HERE
                                            UserOrder r = getTableView().getItems().get(getIndex());

                                            int orderId = Integer.parseInt(r.getORDER_ID());
                                            String com = randomtf.getText();

                                            ConnectionClass conC = new ConnectionClass();
                                            Connection con = conC.getConnection();
                                            try {

                                                CallableStatement ins = con.prepareCall("{?= call COMMENT_ORDER(?,?)}");

                                                ins.setInt(2, orderId);
                                                ins.setString(3, com);

                                                ins.registerOutParameter(1, Types.INTEGER);
                                                ins.execute();
                                                System.out.println(ins.getInt(1));

                                                if (ins.getInt(1) != 1) {
                                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                                    alert.setTitle("Insert Error");
                                                    alert.setHeaderText("Insert Error");
                                                    alert.setContentText("Make sure the branch menu is not already inserted!");
                                                    alert.showAndWait();
                                                }

                                                ins.close();
                                                con.close();
                                                conC.closeConnection();

                                                primaryStage.setScene(HomeScene);

                                                //System.out.println(uname);
                                            } catch (SQLException e) {
                                                e.printStackTrace();
                                            }

                                            primaryStage.setScene(HomeScene);

                                        });

                                        randomvb.getChildren().addAll(l, randomtf, randomok, back);
                                        Scene randomScene = new Scene(randomvb, 500, 500);
                                        randomScene.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());
                                        primaryStage.setScene(randomScene);
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

                actionCol2.setCellFactory(cellFactory2);

                Offertable = new TableView<>();
                Offertable.setItems(offersos);
                Offertable.getColumns().addAll(order_idColumn, rest_nameColumn, loc_nameColumn, AddressColumn, Item_TypeColumn, Item_NameColumn, PriceColumn, OfferColumn, RatingColumn, StatusColumn, actionCol2, actionCol);

                Button goBack_ShowOffer_home = new Button("Back");
                goBack_ShowOffer_home.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setScene(HomeScene);
                    }
                });

                VBox offervbox = new VBox();
                offervbox.getChildren().addAll(goBack_ShowOffer_home, Offertable);
                offervbox.setAlignment(Pos.CENTER);
                offervbox.setId("offervboxbackground");
                Scene offerScene = new Scene(offervbox, 500, 500);
                offerScene.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());

                primaryStage.setScene(offerScene);
            }
        });

        Scene signupScene;
        TextField usernameInput = new TextField();
        usernameInput.setPromptText("Insert User Name...");
        TextField FirstNameInput = new TextField();
        FirstNameInput.setPromptText("Insert First Name...");
        TextField LastNameInput = new TextField();
        LastNameInput.setPromptText("Insert Last Name...");
        TextField emailInput = new TextField();
        emailInput.setPromptText("Insert email...");
        TextField PhoneInput = new TextField();
        PhoneInput.setPromptText("Insert Phone Number...");
        TextField GenderInput = new TextField();
        GenderInput.setPromptText("Insert Gender... Male/Female");
        PasswordField pb = new PasswordField();
        pb.setPromptText("Set a Password");
        Button suokButton = new Button("Ok");
        ChoiceBox genderbox = new ChoiceBox();
        genderbox.getItems().addAll("Male", "Female");
        genderbox.setValue("Female");
        Label liprompt = new Label("Already have an account? Login instead!");
        Button toli = new Button("Login");

        Text a = new Text("Enter User Name: ");
        Text b = new Text("Enter First Name: ");
        Text c = new Text("Enter Last Name: ");
        Text d = new Text("Enter e-mail ID: ");
        Text h = new Text("Enter Phone Number: ");
        Text f = new Text("Select Gender: ");
        Text g = new Text("Set Password: ");

        VBox loginLay = new VBox();
        loginLay.setSpacing(5);
        loginLay.setAlignment(Pos.CENTER);
        loginLay.setId("CommonBackground");
        loginLay.getChildren().addAll(a, usernameInput, b, FirstNameInput, c, LastNameInput, d, emailInput, h, PhoneInput, f, genderbox, g, pb, suokButton, liprompt, toli);
        signupScene = new Scene(loginLay, 500, 500);
        signupScene.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());

        //Start of Admin
        Scene adminHome;
        VBox admhomevb = new VBox();
        Text welcomeAdminText = new Text();
        admhomevb.setAlignment(Pos.CENTER);
        admhomevb.setSpacing(15);
        Label admOptions = new Label("Choose From the Options Below!");
        Button addRest = new Button("Add a Restaurant");
        addRest.setId("NormalButtonsStyle");
        Button addLoc = new Button("Add a Location");
        addLoc.setId("NormalButtonsStyle");
        Button gotoInsertMenu = new Button("Insert Menu Item");
        gotoInsertMenu.setId("NormalButtonsStyle");
        Button AddBranch = new Button("Add a Branch!");
        AddBranch.setId("NormalButtonsStyle");
        Button RateUser = new Button("Rate a User!");
        RateUser.setId("NormalButtonsStyle");
        Button AlcAuth = new Button("Allocate an Authority!");
        AlcAuth.setId("NormalButtonsStyle");

        Button seeReqs = new Button("See Requests");
        seeReqs.setId("NormalButtonsStyle");

        Button logoutadmhome = new Button("Logout");

        admhomevb.getChildren().addAll(welcomeAdminText, admOptions, gotoInsertMenu, addRest, addLoc, AddBranch, RateUser, AlcAuth, seeReqs, logoutadmhome);
        admhomevb.setId("CommonBackground");
        adminHome = new Scene(admhomevb, 500, 500);
        adminHome.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());

        gotoInsertMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Scene insertMenuScene;
                VBox insertMenuvb = new VBox();
                //Label restnameprompt = new Label("Insert Name of the Item:");
                //TextField itemnameinput = new TextField();
                insertMenuvb.setAlignment(Pos.CENTER);
                insertMenuvb.setSpacing(15);
                insertMenuvb.setId("CommonBackground");
                Label itemtypeprompt = new Label("Insert a new item type");
                TextField itemtypeinput = new TextField();
                Button imok = new Button("NEXT");
                //insertMenuvb.getChildren().addAll(restnameprompt, itemnameinput, itemtypeprompt, itemtypeinput, imok);
                Button backfromim_admhome = new Button("Back");
                backfromim_admhome.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setScene(adminHome);
                    }
                });

                Button logoutadmhome1 = new Button("Logout");

                logoutadmhome1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setScene(loginScene);
                    }
                });
                insertMenuvb.getChildren().addAll(itemtypeprompt, itemtypeinput, imok, backfromim_admhome, logoutadmhome1);

                insertMenuScene = new Scene(insertMenuvb, 400, 400);

                insertMenuScene.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());

                imok.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        ConnectionClass conC = new ConnectionClass();
                        Connection con = conC.getConnection();
                        try {
                            String sql = "INSERT INTO MENU_TYPES (TYPE_ID, ITEM_TYPE) VALUES (TYPE_ID_SEQ.NEXTVAL, ?)";
                            PreparedStatement pst = con.prepareStatement(sql);
                            pst.setString(1, itemtypeinput.getText());
                            //pst.setString(2, itemnameinput.getText());
                            ResultSet rs = pst.executeQuery();
                            pst.close();
                            con.close();
                            conC.closeConnection();
                            primaryStage.setScene(adminHome);
                            //System.out.println(uname);
                        } catch (SQLException e) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Insert Error");
                            alert.setHeaderText("Insert Error");
                            alert.setContentText("Make sure the type isn't already inserted!");
                            alert.showAndWait();
                            e.printStackTrace();
                        }
                    }
                });

                primaryStage.setScene(insertMenuScene);
            }
        });

        // AUTH
        Scene authorityHome;
        VBox authhomevb = new VBox();
        Text welcomeAuthText = new Text();
        authhomevb.setAlignment(Pos.CENTER);
        authhomevb.setSpacing(20);
        authhomevb.setId("CommonBackground");
        //Label greetauth = new Label("Welcome Authority!");
        Label authOptions = new Label("Select an Option!");

        Button setBranchMenuB = new Button("Set up a branch menu item");
        setBranchMenuB.setId("NormalButtonsStyle");
        Button editOffer = new Button("Edit an Offer!");
        editOffer.setId("NormalButtonsStyle");
        Button removeBranchMenuB = new Button("Remove a branch menu item");
        removeBranchMenuB.setId("NormalButtonsStyle");

        Button seeOrders = new Button("See Orders");
        seeOrders.setId("NormalButtonsStyle");

        Button seeReport = new Button("See Menu Report");
        seeReport.setId("NormalButtonsStyle");

        Button logoutauthhome = new Button("Logout");
        logoutauthhome.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(loginScene);
            }
        });

        authhomevb.getChildren().addAll(welcomeAuthText, authOptions, setBranchMenuB, editOffer, removeBranchMenuB, seeOrders, seeReport, logoutauthhome);
        authorityHome = new Scene(authhomevb, 500, 500);
        authorityHome.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());

        setBranchMenuB.setOnAction(new EventHandler<ActionEvent>() {
            String MNSelectedLocTHR = new String();
            String MNSelectedResTHR = new String();
            String MNSelectedItT = new String();
            String MNSelectedItN = new String();

            public void handle(ActionEvent event) {
                Scene menratscene;
                GridPane RateMN = new GridPane();
                RateMN.setId("CommonBackground");
                RateMN.setVgap(20);
                RateMN.setHgap(20);
                ConnectionClass conC = new ConnectionClass();
                Connection con = conC.getConnection();

                try {

                    String sql = "SELECT LOCATION_NAME FROM LOCATIONS l join RESTAURANTS_AUTHORITY ra on (l.LOCATION_ID = ra.LOCATION_ID) join AUTHORITIES a on (a.AUTH_ID = ra.AUTH_ID) where AUTH_USER_NAME = ?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, uname);
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        MNSelectedLocTHR = rs.getString(1);
                    }
                    pst.close();
                    con.close();
                    conC.closeConnection();

                    //System.out.println(uname);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                conC = new ConnectionClass();
                con = conC.getConnection();
                try {

                    String sql = "SELECT RESTAURANT_NAME FROM RESTAURANTS r join RESTAURANTS_AUTHORITY ra on (r.REST_ID = ra.REST_ID) join AUTHORITIES a on (a.AUTH_ID = ra.AUTH_ID) where AUTH_USER_NAME = ?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, uname);

                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        MNSelectedResTHR = rs.getString(1);
                    }
                    pst.close();
                    con.close();
                    conC.closeConnection();
                    //System.out.println(uname);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                //System.out.println(MNSelectedLocTHR);
                //System.out.println(MNSelectedResTHR);
                ChoiceBox MNRit = new ChoiceBox();
                MNRit.setId("NormalButtonsStyle");
                conC = new ConnectionClass();
                con = conC.getConnection();
                List MNRlistits = new ArrayList();

                //System.out.println(MNSelectedLocTHR);
                //System.out.println(MNSelectedResTHR);
                try {

                    String sql = "select DISTINCT ITEM_TYPE from menu_types";
                    PreparedStatement pst = con.prepareStatement(sql);

                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        MNRlistits.add(rs.getString(1));
                    }
                    pst.close();
                    con.close();
                    conC.closeConnection();

                    //System.out.println(uname);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                MNRit.getItems().addAll(MNRlistits);
                MNRit.setValue(MNRlistits.get(0));
                MNRit.setPrefSize(250, 40);
                RateMN.add(MNRit, 2, 1);

                Label np = new Label("Item Type not present?");
                np.setId("NormalText");
                RateMN.add(np, 2, 3);

                Button b = new Button("Request");
                RateMN.add(b, 3, 3);

                TextField mname = new TextField();
                mname.setId("NormalButtonsStyle");
                mname.setPrefSize(250, 40);
                mname.setPromptText("Enter Item Name");
                RateMN.add(mname, 2, 5);

                TextField mnating = new TextField();
                mnating.setPrefSize(250, 40);
                mnating.setId("NormalButtonsStyle");
                mnating.setPromptText("Enter Price");
                RateMN.add(mnating, 2, 7);

                TextField mnc = new TextField();
                mnc.setPrefSize(250, 40);
                mnc.setId("NormalButtonsStyle");
                mnc.setPromptText("Enter Offer");
                RateMN.add(mnc, 2, 9);

                Button backaddmenu_authhome = new Button("Back");
                RateMN.add(backaddmenu_authhome, 3, 10);
                backaddmenu_authhome.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setScene(authorityHome);
                    }
                });

                Button logoutaddmenu = new Button("Logout");
                RateMN.add(logoutaddmenu, 3, 11);
                logoutaddmenu.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setScene(loginScene);
                    }
                });

                b.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Scene insertMenuScene;
                        VBox insertMenuvb = new VBox();
                        //Label restnameprompt = new Label("Insert Name of the Item:");
                        //TextField itemnameinput = new TextField();
                        insertMenuvb.setAlignment(Pos.CENTER);
                        insertMenuvb.setSpacing(15);
                        insertMenuvb.setId("CommonBackground");
                        Label itemtypeprompt = new Label("Request a new item type");
                        TextField itemtypeinput = new TextField();
                        Button imok = new Button("NEXT");
                        //insertMenuvb.getChildren().addAll(restnameprompt, itemnameinput, itemtypeprompt, itemtypeinput, imok);
                        Button backfromim_admhome = new Button("Back");
                        backfromim_admhome.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                primaryStage.setScene(authorityHome);
                            }
                        });

                        Button logout = new Button("Logout");

                        logout.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                primaryStage.setScene(loginScene);
                            }
                        });
                        insertMenuvb.getChildren().addAll(itemtypeprompt, itemtypeinput, imok, backfromim_admhome, logout);

                        insertMenuScene = new Scene(insertMenuvb, 400, 400);

                        insertMenuScene.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());

                        imok.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                ConnectionClass conC = new ConnectionClass();
                                Connection con = conC.getConnection();
                                try {
                                    String sql = "INSERT INTO REQUEST_MENU_TYPES (REQ_TYPE_ID, REQ_ITEM_TYPE) VALUES (REQ_TYPE_ID_SEQ.NEXTVAL, ?)";
                                    PreparedStatement pst = con.prepareStatement(sql);
                                    pst.setString(1, itemtypeinput.getText());
                                    //pst.setString(2, itemnameinput.getText());
                                    ResultSet rs = pst.executeQuery();
                                    pst.close();
                                    con.close();
                                    conC.closeConnection();
                                    primaryStage.setScene(authorityHome);
                                    //System.out.println(uname);
                                } catch (SQLException e) {

                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setTitle("Insert Error");
                                    alert.setHeaderText("Insert Error");
                                    alert.setContentText("Make sure the type isn't already requested!");
                                    alert.showAndWait();
                                    e.printStackTrace();
                                }
                            }
                        });

                        primaryStage.setScene(insertMenuScene);
                    }
                });

                Button MNRok = new Button("Insert");

                RateMN.add(MNRok, 3, 9);

                MNRok.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        MNSelectedItT = (String) MNRit.getValue();
                        MNSelectedItN = mname.getText();

                        ConnectionClass conC = new ConnectionClass();
                        Connection con = conC.getConnection();
                        try {

                            CallableStatement ins = con.prepareCall("{?= call SET_BRANCH_MENU(?,?,?,?,?,'YES',?)}");

                            ins.setString(2, MNSelectedResTHR);
                            ins.setString(3, MNSelectedLocTHR);
                            ins.setString(4, MNSelectedItT);
                            ins.setString(5, MNSelectedItN);
                            ins.setDouble(6, Double.parseDouble(mnating.getText()));
                            ins.setString(7, mnc.getText());
                            ins.registerOutParameter(1, Types.INTEGER);
                            ins.execute();
                            System.out.println(ins.getInt(1));

                            if (ins.getInt(1) != 1) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Insert Error");
                                alert.setHeaderText("Insert Error");
                                alert.setContentText("Make sure the branch menu is not already inserted!");
                                alert.showAndWait();
                            }

                            ins.close();
                            con.close();
                            conC.closeConnection();

                            primaryStage.setScene(authorityHome);

                            //System.out.println(uname);
                        } catch (SQLException e) {

                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Insert Error");
                            alert.setHeaderText("Insert Error");
                            alert.setContentText("Make sure you've provided a rating!");
                            alert.showAndWait();
                            e.printStackTrace();
                        }
                        //INSERT INTO RESTAURANTS_BRANCHES (REST_ID, LOCATION_ID, MANAGER_NAME, ADDRESS, CONTACT_NUMBER, OPEN_TIME, CLOSE_TIME, WEEKLY_HOLIDAY) VALUES (?, ?, ?, ?, ?, ?, ?, ?)    
                        //primaryStage.setScene(adminHome);

                    }
                });

                menratscene = new Scene(RateMN, 500, 500);
                menratscene.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());

                primaryStage.setScene(menratscene);

            }
        });

        editOffer.setOnAction(new EventHandler<ActionEvent>() {
            String MNSelectedLocTHR = new String();
            String MNSelectedResTHR = new String();
            String MNSelectedItT = new String();
            String MNSelectedItN = new String();

            public void handle(ActionEvent event) {
                Scene menratscene;
                GridPane RateMN = new GridPane();
                RateMN.setId("CommonBackground");
                RateMN.setVgap(20);
                RateMN.setHgap(20);
                ConnectionClass conC = new ConnectionClass();
                Connection con = conC.getConnection();

                try {

                    String sql = "SELECT LOCATION_NAME FROM LOCATIONS l join RESTAURANTS_AUTHORITY ra on (l.LOCATION_ID = ra.LOCATION_ID) join AUTHORITIES a on (a.AUTH_ID = ra.AUTH_ID) where AUTH_USER_NAME = ?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, uname);
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        MNSelectedLocTHR = rs.getString(1);
                    }
                    pst.close();
                    con.close();
                    conC.closeConnection();

                    //System.out.println(uname);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                conC = new ConnectionClass();
                con = conC.getConnection();
                try {

                    String sql = "SELECT RESTAURANT_NAME FROM RESTAURANTS r join RESTAURANTS_AUTHORITY ra on (r.REST_ID = ra.REST_ID) join AUTHORITIES a on (a.AUTH_ID = ra.AUTH_ID) where AUTH_USER_NAME = ?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, uname);

                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        MNSelectedResTHR = rs.getString(1);
                    }
                    pst.close();
                    con.close();
                    conC.closeConnection();
                    //System.out.println(uname);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                //System.out.println(MNSelectedLocTHR);
                //System.out.println(MNSelectedResTHR);
                ChoiceBox MNRit = new ChoiceBox();
                MNRit.setId("NormalButtonsStyle");
                conC = new ConnectionClass();
                con = conC.getConnection();
                List MNRlistits = new ArrayList();

                //System.out.println(MNSelectedLocTHR);
                //System.out.println(MNSelectedResTHR);
                try {

                    String sql = "select DISTINCT mi.ITEM_TYPE from BRANCH_MENUS bm join RESTAURANTS r on (bm.REST_ID = r.REST_ID) join LOCATIONS l on (bm.LOCATION_ID = l.LOCATION_ID) join MENU_ITEMS mi on (mi.ITEM_ID = bm.ITEM_ID) where RESTAURANT_NAME = ? AND LOCATION_NAME = ?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, MNSelectedResTHR);
                    pst.setString(2, MNSelectedLocTHR);

                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        MNRlistits.add(rs.getString(1));
                    }
                    pst.close();
                    con.close();
                    conC.closeConnection();

                    //System.out.println(uname);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                MNRit.getItems().addAll(MNRlistits);
                MNRit.setValue(MNRlistits.get(0));
                MNRit.setPrefSize(250, 40);
                RateMN.add(MNRit, 2, 6);

                Button backaddoffer_authhome = new Button("Back");
                RateMN.add(backaddoffer_authhome, 3, 8);
                backaddoffer_authhome.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setScene(authorityHome);
                    }
                });

                Button logoutaddoffer = new Button("Logout");
                RateMN.add(logoutaddoffer, 3, 9);
                logoutaddoffer.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setScene(loginScene);
                    }
                });

                Button b = new Button("Choose");
                RateMN.add(b, 2, 7);

                menratscene = new Scene(RateMN, 500, 500);

                b.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        MNSelectedItT = (String) MNRit.getValue();

                        Scene insideb;
                        VBox bpvb = new VBox();
                        bpvb.setAlignment(Pos.CENTER);
                        bpvb.setSpacing(20);
                        bpvb.setId("CommonBackground");
                        ChoiceBox bit = new ChoiceBox();
                        ConnectionClass conC = new ConnectionClass();
                        Connection con = conC.getConnection();
                        List blistits = new ArrayList();

                        //System.out.println(MNSelectedLocTHR);
                        //System.out.println(MNSelectedResTHR);
                        try {

                            String sql = "select DISTINCT mi.ITEM_NAME from BRANCH_MENUS bm join RESTAURANTS r on (bm.REST_ID = r.REST_ID) join LOCATIONS l on (bm.LOCATION_ID = l.LOCATION_ID) join MENU_ITEMS mi on (mi.ITEM_ID = bm.ITEM_ID) where RESTAURANT_NAME = ? AND LOCATION_NAME = ? AND ITEM_TYPE = ?";
                            PreparedStatement pst = con.prepareStatement(sql);
                            pst.setString(1, MNSelectedResTHR);
                            pst.setString(2, MNSelectedLocTHR);
                            pst.setString(3, MNSelectedItT);

                            ResultSet rs = pst.executeQuery();
                            while (rs.next()) {
                                blistits.add(rs.getString(1));
                            }
                            pst.close();
                            con.close();
                            conC.closeConnection();

                            //System.out.println(uname);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        bit.getItems().addAll(blistits);
                        bit.setValue(blistits.get(0));
                        bit.setPrefSize(250, 40);

                        // menratscene
                        Button backinsideb_menrat = new Button("Back");
                        backinsideb_menrat.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                primaryStage.setScene(menratscene);
                            }
                        });

                        Button logoutinsideb = new Button("Logout");
                        logoutinsideb.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                primaryStage.setScene(loginScene);
                            }
                        });

                        bit.setId("NormalButtonsStyle");
                        TextField mnc = new TextField();
                        mnc.setPrefSize(250, 40);
                        mnc.setPromptText("Enter Offer");

                        mnc.setId("NormalButtonsStyle");
                        Button bok = new Button("Insert");

                        bok.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                MNSelectedItN = (String) bit.getValue();

                                System.out.println(MNSelectedItN);

                                ConnectionClass conC = new ConnectionClass();
                                Connection con = conC.getConnection();
                                try {

                                    CallableStatement ins = con.prepareCall("{?= call SET_BRANCH_MENU_OFFER(?,?,?,?,?)}");

                                    ins.setString(2, MNSelectedResTHR);
                                    ins.setString(3, MNSelectedLocTHR);
                                    ins.setString(4, MNSelectedItT);
                                    ins.setString(5, MNSelectedItN);
                                    ins.setString(6, mnc.getText());
                                    ins.registerOutParameter(1, Types.INTEGER);
                                    ins.execute();
                                    System.out.println(ins.getInt(1));

                                    if (ins.getInt(1) != 1) {
                                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                        alert.setTitle("Insert Error");
                                        alert.setHeaderText("Insert Error");
                                        alert.setContentText("Make sure the branch menu exists!");
                                        alert.showAndWait();
                                    }

                                    ins.close();
                                    con.close();
                                    conC.closeConnection();

                                    primaryStage.setScene(authorityHome);

                                    //System.out.println(uname);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                //INSERT INTO RESTAURANTS_BRANCHES (REST_ID, LOCATION_ID, MANAGER_NAME, ADDRESS, CONTACT_NUMBER, OPEN_TIME, CLOSE_TIME, WEEKLY_HOLIDAY) VALUES (?, ?, ?, ?, ?, ?, ?, ?)    
                                //primaryStage.setScene(adminHome);

                            }
                        });
                        bpvb.getChildren().addAll(bit, mnc, bok, backinsideb_menrat, logoutinsideb);
                        insideb = new Scene(bpvb, 500, 500);
                        insideb.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());
                        primaryStage.setScene(insideb);

                    }
                });

                menratscene.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());

                primaryStage.setScene(menratscene);

            }
        });

        removeBranchMenuB.setOnAction(new EventHandler<ActionEvent>() {
            String MNSelectedLocTHR = new String();
            String MNSelectedResTHR = new String();
            String MNSelectedItT = new String();
            String MNSelectedItN = new String();

            public void handle(ActionEvent event) {
                Scene removeBMenu;
                GridPane RateMN = new GridPane();
                RateMN.setId("CommonBackground");
                RateMN.setVgap(20);
                RateMN.setHgap(20);
                ConnectionClass conC = new ConnectionClass();
                Connection con = conC.getConnection();

                try {

                    String sql = "SELECT LOCATION_NAME FROM LOCATIONS l join RESTAURANTS_AUTHORITY ra on (l.LOCATION_ID = ra.LOCATION_ID) join AUTHORITIES a on (a.AUTH_ID = ra.AUTH_ID) where AUTH_USER_NAME = ?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, uname);
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        MNSelectedLocTHR = rs.getString(1);
                    }
                    pst.close();
                    con.close();
                    conC.closeConnection();

                    //System.out.println(uname);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                conC = new ConnectionClass();
                con = conC.getConnection();
                try {

                    String sql = "SELECT RESTAURANT_NAME FROM RESTAURANTS r join RESTAURANTS_AUTHORITY ra on (r.REST_ID = ra.REST_ID) join AUTHORITIES a on (a.AUTH_ID = ra.AUTH_ID) where AUTH_USER_NAME = ?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, uname);

                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        MNSelectedResTHR = rs.getString(1);
                    }
                    pst.close();
                    con.close();
                    conC.closeConnection();
                    //System.out.println(uname);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                //System.out.println(MNSelectedLocTHR);
                //System.out.println(MNSelectedResTHR);
                ChoiceBox MNRit = new ChoiceBox();
                MNRit.setId("NormalButtonsStyle");
                conC = new ConnectionClass();
                con = conC.getConnection();
                List MNRlistits = new ArrayList();

                //System.out.println(MNSelectedLocTHR);
                //System.out.println(MNSelectedResTHR);
                try {

                    String sql = "select DISTINCT mi.ITEM_TYPE from BRANCH_MENUS bm join RESTAURANTS r on (bm.REST_ID = r.REST_ID) join LOCATIONS l on (bm.LOCATION_ID = l.LOCATION_ID) join MENU_ITEMS mi on (mi.ITEM_ID = bm.ITEM_ID) where RESTAURANT_NAME = ? AND LOCATION_NAME = ?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, MNSelectedResTHR);
                    pst.setString(2, MNSelectedLocTHR);

                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        MNRlistits.add(rs.getString(1));
                    }
                    pst.close();
                    con.close();
                    conC.closeConnection();

                    //System.out.println(uname);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                MNRit.getItems().addAll(MNRlistits);
                MNRit.setValue(MNRlistits.get(0));
                MNRit.setPrefSize(250, 40);
                RateMN.add(MNRit, 2, 6);

                Button backaddoffer_authhome = new Button("Back");
                RateMN.add(backaddoffer_authhome, 3, 8);
                backaddoffer_authhome.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setScene(authorityHome);
                    }
                });

                Button logoutaddoffer = new Button("Logout");
                RateMN.add(logoutaddoffer, 3, 9);
                logoutaddoffer.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setScene(loginScene);
                    }
                });

                Button b = new Button("Choose");
                RateMN.add(b, 2, 7);

                removeBMenu = new Scene(RateMN, 500, 500);

                b.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        MNSelectedItT = (String) MNRit.getValue();

                        Scene insideb;
                        VBox bpvb = new VBox();
                        bpvb.setAlignment(Pos.CENTER);
                        bpvb.setSpacing(20);
                        bpvb.setId("CommonBackground");
                        ChoiceBox bit = new ChoiceBox();
                        ConnectionClass conC = new ConnectionClass();
                        Connection con = conC.getConnection();
                        List blistits = new ArrayList();

                        //System.out.println(MNSelectedLocTHR);
                        //System.out.println(MNSelectedResTHR);
                        try {

                            String sql = "select DISTINCT mi.ITEM_NAME from BRANCH_MENUS bm join RESTAURANTS r on (bm.REST_ID = r.REST_ID) join LOCATIONS l on (bm.LOCATION_ID = l.LOCATION_ID) join MENU_ITEMS mi on (mi.ITEM_ID = bm.ITEM_ID) where RESTAURANT_NAME = ? AND LOCATION_NAME = ? AND ITEM_TYPE = ?";
                            PreparedStatement pst = con.prepareStatement(sql);
                            pst.setString(1, MNSelectedResTHR);
                            pst.setString(2, MNSelectedLocTHR);
                            pst.setString(3, MNSelectedItT);

                            ResultSet rs = pst.executeQuery();
                            while (rs.next()) {
                                blistits.add(rs.getString(1));
                            }
                            pst.close();
                            con.close();
                            conC.closeConnection();

                            //System.out.println(uname);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        bit.getItems().addAll(blistits);
                        bit.setValue(blistits.get(0));
                        bit.setPrefSize(250, 40);

                        // menratscene
                        Button backinsideb_menrat = new Button("Back");
                        backinsideb_menrat.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                primaryStage.setScene(removeBMenu);
                            }
                        });

                        Button logoutinsideb = new Button("Logout");
                        logoutinsideb.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                primaryStage.setScene(loginScene);
                            }
                        });

                        bit.setId("NormalButtonsStyle");

                        Button bok = new Button("Delete");

                        bok.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                MNSelectedItN = (String) bit.getValue();

                                System.out.println(MNSelectedItN);

                                ConnectionClass conC = new ConnectionClass();
                                Connection con = conC.getConnection();
                                try {

                                    CallableStatement ins = con.prepareCall("{?= call DELETE_BRANCH_MENU(?,?,?,?)}");

                                    ins.setString(2, MNSelectedResTHR);
                                    ins.setString(3, MNSelectedLocTHR);
                                    ins.setString(4, MNSelectedItT);
                                    ins.setString(5, MNSelectedItN);
                                    ins.registerOutParameter(1, Types.INTEGER);
                                    ins.execute();
                                    System.out.println(ins.getInt(1));

                                    if (ins.getInt(1) != 1) {
                                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                        alert.setTitle("Insert Error");
                                        alert.setHeaderText("Insert Error");
                                        alert.setContentText("Make sure the branch menu exists!");
                                        alert.showAndWait();
                                    }

                                    ins.close();
                                    con.close();
                                    conC.closeConnection();

                                    primaryStage.setScene(authorityHome);

                                    //System.out.println(uname);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                //INSERT INTO RESTAURANTS_BRANCHES (REST_ID, LOCATION_ID, MANAGER_NAME, ADDRESS, CONTACT_NUMBER, OPEN_TIME, CLOSE_TIME, WEEKLY_HOLIDAY) VALUES (?, ?, ?, ?, ?, ?, ?, ?)    
                                //primaryStage.setScene(adminHome);

                            }
                        });
                        bpvb.getChildren().addAll(bit, bok, backinsideb_menrat, logoutinsideb);
                        insideb = new Scene(bpvb, 500, 500);
                        insideb.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());
                        primaryStage.setScene(insideb);

                    }
                });

                removeBMenu.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());

                primaryStage.setScene(removeBMenu);

            }

        });

        seeOrders.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ConnectionClass conC = new ConnectionClass();
                Connection con = conC.getConnection();
                ObservableList<Orders> offersos = FXCollections.observableArrayList();
                try {
                    String sql = "SELECT  o.ORDER_ID, u.FIRSTNAME || ' ' || u.LASTNAME AS \"FULL_NAME\", u.USER_NUMBER, u.EMAIL, i.ITEM_TYPE, i.ITEM_NAME, o.AMOUNT, o.ORDER_DATE, o.COMMENTS, o.ORDER_STATUS from USERS_MENU_ORDER o join RESTAURANTS_AUTHORITY r on(o.REST_ID = r.REST_ID and o.LOCATION_ID = r.LOCATION_ID) join AUTHORITIES a on (r.AUTH_ID = a.AUTH_ID) join USERS u on (o.USER_ID = u.USER_ID) join MENU_ITEMS i on (i.ITEM_ID = o.ITEM_ID) where a.AUTH_USER_NAME = ? and o.order_status <> 'Paid'";

                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, uname);

                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        String str1 = Integer.toString(rs.getInt(1));
                        String str2 = rs.getString(2);
                        String str3 = rs.getString(3);
                        String str4 = rs.getString(4);
                        String str5 = rs.getString(5);
                        String str6 = rs.getString(6);
                        String str7 = Integer.toString(rs.getInt(7));
                        String str8 = rs.getString(8);
                        String str9 = rs.getString(9);
                        String str10 = rs.getString(10);
                        offersos.add(new Orders(str1, str2, str3, str4, str5, str6, str7, str8, str9, str10));
                    }
                    pst.close();
                    con.close();
                    conC.closeConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                TableView<Orders> Offertable;

                TableColumn<Orders, String> order_idColumn = new TableColumn<>("ORDER_ID");
                order_idColumn.setMinWidth(200);
                order_idColumn.setCellValueFactory(new PropertyValueFactory<>("ORDER_ID"));

                TableColumn<Orders, String> rest_nameColumn = new TableColumn<>("FULL_NAME");
                rest_nameColumn.setMinWidth(200);
                rest_nameColumn.setCellValueFactory(new PropertyValueFactory<>("FULL_NAME"));

                TableColumn<Orders, String> loc_nameColumn = new TableColumn<>("USER_NUMBER");
                loc_nameColumn.setMinWidth(200);
                loc_nameColumn.setCellValueFactory(new PropertyValueFactory<>("USER_NUMBER"));

                TableColumn<Orders, String> AddressColumn = new TableColumn<>("EMAIL");
                AddressColumn.setMinWidth(200);
                AddressColumn.setCellValueFactory(new PropertyValueFactory<>("EMAIL"));

                TableColumn<Orders, String> Item_TypeColumn = new TableColumn<>("ITEM_TYPE");
                Item_TypeColumn.setMinWidth(200);
                Item_TypeColumn.setCellValueFactory(new PropertyValueFactory<>("ITEM_TYPE"));

                TableColumn<Orders, String> Item_NameColumn = new TableColumn<>("ITEM_NAME");
                Item_NameColumn.setMinWidth(200);
                Item_NameColumn.setCellValueFactory(new PropertyValueFactory<>("ITEM_NAME"));

                TableColumn<Orders, String> PriceColumn = new TableColumn<>("AMOUNT");
                PriceColumn.setMinWidth(100);
                PriceColumn.setCellValueFactory(new PropertyValueFactory<>("AMOUNT"));

                TableColumn<Orders, String> OfferColumn = new TableColumn<>("ORDER_DATE");
                OfferColumn.setMinWidth(200);
                OfferColumn.setCellValueFactory(new PropertyValueFactory<>("ORDER_DATE"));

                TableColumn<Orders, String> RatingColumn = new TableColumn<>("COMMENTS");
                RatingColumn.setMinWidth(200);
                RatingColumn.setCellValueFactory(new PropertyValueFactory<>("COMMENTS"));

                TableColumn<Orders, String> StatusColumn = new TableColumn<>("ORDER_STATUS");
                StatusColumn.setMinWidth(200);
                StatusColumn.setCellValueFactory(new PropertyValueFactory<>("ORDER_STATUS"));

                TableColumn actionCol = new TableColumn("Delivery");
                actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

                Callback<TableColumn<Orders, String>, TableCell<Orders, String>> cellFactory
                        = //
                        new Callback<TableColumn<Orders, String>, TableCell<Orders, String>>() {

                    @Override
                    public TableCell call(final TableColumn<Orders, String> param) {
                        final TableCell<Orders, String> cell = new TableCell<Orders, String>() {

                            final Button btn = new Button("Delivered");

                            @Override
                            public void updateItem(String item, boolean empty) {

                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {

                                        Orders r = getTableView().getItems().get(getIndex());

                                        int orderId = Integer.parseInt(r.getORDER_ID());

                                        ConnectionClass conC = new ConnectionClass();
                                        Connection con = conC.getConnection();
                                        try {

                                            CallableStatement ins = con.prepareCall("{?= call DELIVER_ORDER(?)}");

                                            ins.setInt(2, orderId);

                                            ins.registerOutParameter(1, Types.INTEGER);
                                            ins.execute();
                                            System.out.println(ins.getInt(1));

                                            if (ins.getInt(1) != 1) {
                                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                                alert.setTitle("Insert Error");
                                                alert.setHeaderText("Insert Error");
                                                alert.setContentText("Make sure the branch menu exists!");
                                                alert.showAndWait();
                                            }

                                            ins.close();
                                            con.close();
                                            conC.closeConnection();

                                            primaryStage.setScene(authorityHome);

                                            //System.out.println(uname);
                                        } catch (SQLException e) {

                                            e.printStackTrace();
                                        }

                                        primaryStage.setScene(authorityHome);

                                        // eikhane sql boshbe
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

                actionCol.setCellFactory(cellFactory);

                //new button column
                TableColumn actionCol2 = new TableColumn("Edit Status");
                actionCol2.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

                Callback<TableColumn<Orders, String>, TableCell<Orders, String>> cellFactory2
                        = //
                        new Callback<TableColumn<Orders, String>, TableCell<Orders, String>>() {

                    @Override
                    public TableCell call(final TableColumn<Orders, String> param) {
                        final TableCell<Orders, String> cell = new TableCell<Orders, String>() {

                            final Button btn = new Button("Status");

                            @Override
                            public void updateItem(String item, boolean empty) {

                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        VBox randomvb = new VBox();
                                        randomvb.setId("CommonBackground");
                                        randomvb.setAlignment(Pos.CENTER);
                                        randomvb.setSpacing(20);
                                        Label l = new Label("Enter order status here");
                                        TextField randomtf = new TextField();
                                        Button randomok = new Button("Ok");
                                        Button back = new Button("Back");

                                        back.setOnAction(new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent event) {
                                                primaryStage.setScene(authorityHome);
                                            }
                                        });

                                        randomok.setOnAction(event2 -> {
                                            ///SQL HERE
                                            Orders r = getTableView().getItems().get(getIndex());

                                            int orderId = Integer.parseInt(r.getORDER_ID());
                                            String sts = randomtf.getText();

                                            ConnectionClass conC = new ConnectionClass();
                                            Connection con = conC.getConnection();
                                            try {

                                                CallableStatement ins = con.prepareCall("{?= call STATUS_ORDER(?,?)}");

                                                ins.setInt(2, orderId);
                                                ins.setString(3, sts);

                                                ins.registerOutParameter(1, Types.INTEGER);
                                                ins.execute();
                                                System.out.println(ins.getInt(1));

                                                if (ins.getInt(1) != 1) {
                                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                                    alert.setTitle("Insert Error");
                                                    alert.setHeaderText("Insert Error");
                                                    alert.setContentText("Make sure the order exists!");
                                                    alert.showAndWait();
                                                }

                                                ins.close();
                                                con.close();
                                                conC.closeConnection();

                                                primaryStage.setScene(authorityHome);

                                                //System.out.println(uname);
                                            } catch (SQLException e) {
                                                e.printStackTrace();
                                            }

                                            primaryStage.setScene(authorityHome);

                                        });

                                        randomvb.getChildren().addAll(l, randomtf, randomok, back);
                                        Scene randomScene = new Scene(randomvb, 500, 500);
                                        randomScene.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());
                                        primaryStage.setScene(randomScene);
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

                actionCol2.setCellFactory(cellFactory2);

                //another new button
                TableColumn actionCol3 = new TableColumn("Payment");
                actionCol3.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

                Callback<TableColumn<Orders, String>, TableCell<Orders, String>> cellFactory3
                        = //
                        new Callback<TableColumn<Orders, String>, TableCell<Orders, String>>() {

                    @Override
                    public TableCell call(final TableColumn<Orders, String> param) {
                        final TableCell<Orders, String> cell = new TableCell<Orders, String>() {

                            final Button btn = new Button("Paid");

                            @Override
                            public void updateItem(String item, boolean empty) {

                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {

                                        Orders r = getTableView().getItems().get(getIndex());

                                        int orderId = Integer.parseInt(r.getORDER_ID());

                                        ConnectionClass conC = new ConnectionClass();
                                        Connection con = conC.getConnection();
                                        try {

                                            CallableStatement ins = con.prepareCall("{?= call PAY_ORDER(?)}");

                                            ins.setInt(2, orderId);

                                            ins.registerOutParameter(1, Types.INTEGER);
                                            ins.execute();
                                            System.out.println(ins.getInt(1));

                                            if (ins.getInt(1) != 1) {
                                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                                alert.setTitle("Insert Error");
                                                alert.setHeaderText("Insert Error");
                                                alert.setContentText("Make sure the order exists!");
                                                alert.showAndWait();
                                            }

                                            ins.close();
                                            con.close();
                                            conC.closeConnection();

                                            primaryStage.setScene(authorityHome);

                                            //System.out.println(uname);
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }

                                        primaryStage.setScene(authorityHome);

                                        // eikhane sql boshbe
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }

                        };
                        return cell;
                    }
                };

                actionCol3.setCellFactory(cellFactory3);

                Offertable = new TableView<>();
                Offertable.setItems(offersos);
                Offertable.getColumns().addAll(order_idColumn, rest_nameColumn, loc_nameColumn, AddressColumn, Item_TypeColumn, Item_NameColumn, PriceColumn, OfferColumn, RatingColumn, StatusColumn, actionCol2, actionCol, actionCol3);

                Button goBack_ShowOffer_home = new Button("Back");
                goBack_ShowOffer_home.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setScene(authorityHome);
                    }
                });

                VBox offervbox = new VBox();
                offervbox.getChildren().addAll(goBack_ShowOffer_home, Offertable);
                offervbox.setAlignment(Pos.CENTER);
                offervbox.setId("offervboxbackground");
                Scene offerScene = new Scene(offervbox, 500, 500);
                offerScene.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());

                primaryStage.setScene(offerScene);
            }
        });

        seeReport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                VBox g = new VBox();
                ObservableList<PieChart.Data> pi;

                pi = FXCollections.observableArrayList();

                ConnectionClass conC = new ConnectionClass();
                Connection con = conC.getConnection();
                ObservableList<Report> offersos = FXCollections.observableArrayList();
                try {
                    String sql = "select i.ITEM_TYPE, i.ITEM_NAME, sum(o.AMOUNT) as \"Total Sold\" from USERS_MENU_ORDER o join RESTAURANTS_AUTHORITY au on(o.REST_ID = au.REST_ID and o.LOCATION_ID = au.LOCATION_ID) join AUTHORITIES a on (a.AUTH_ID = au.AUTH_ID) join MENU_ITEMS i on (o.ITEM_ID = i.ITEM_ID) where a.AUTH_USER_NAME = ? GROUP BY o.ITEM_ID, i.ITEM_TYPE, i.ITEM_NAME order by \"Total Sold\" desc";

                    PreparedStatement pst = con.prepareStatement(sql);

                    pst.setString(1, uname);

                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        String str1 = rs.getString(1);
                        String str2 = rs.getString(2);
                        String str3 = rs.getString(3);
                        int i = Integer.parseInt(str3);

                        pi.add(new PieChart.Data(str2 + " (" + str1 + "): " + i, i));

                    }
                    pst.close();
                    con.close();
                    conC.closeConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                /*
                TableView<Report> Offertable;

                TableColumn<Report, String> item_typeColumn = new TableColumn<>("Item_Type");
                item_typeColumn.setMinWidth(200);
                item_typeColumn.setCellValueFactory(new PropertyValueFactory<>("Item_Type"));

                TableColumn<Report, String> item_nameColumn = new TableColumn<>("Item_Name");
                item_nameColumn.setMinWidth(200);
                item_nameColumn.setCellValueFactory(new PropertyValueFactory<>("Item_Name"));

                TableColumn<Report, String> tot_soldColumn = new TableColumn<>("Total_Sold");
                tot_soldColumn.setMinWidth(200);
                tot_soldColumn.setCellValueFactory(new PropertyValueFactory<>("Total_Sold"));

                Offertable = new TableView<>();
                Offertable.setItems(offersos);
                Offertable.getColumns().addAll(item_typeColumn, item_nameColumn, tot_soldColumn);
                 */
                Button goBack_ShowOffer_home = new Button("Back");
                goBack_ShowOffer_home.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setScene(authorityHome);
                    }
                });

                final PieChart chart = new PieChart(pi);

                chart.setTitle("Sold Items");

                //chart.setPrefSize(500, 500);
                //chart.setPrefHeight(500);
                //chart.setPrefWidth(500);
                g.setAlignment(Pos.CENTER);
                chart.setLabelLineLength(10);
                chart.setLegendSide(Side.LEFT);
                
                goBack_ShowOffer_home.setPrefSize(150, 50);


                g.getChildren().add(chart);
                
                g.getChildren().add(goBack_ShowOffer_home);

                g.setId("offervboxbackground");
                Scene offerScene = new Scene(g, 1000, 1000);
                
                primaryStage.setTitle("Menu Report");
                
                //offerScene.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());

                primaryStage.setScene(offerScene);
            }
        });

        // SHESH
        logoutadmhome.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(loginScene);
            }
        });

        addRest.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Scene insertRestScene;
                VBox insertRestvb = new VBox();
                insertRestvb.setAlignment(Pos.CENTER);
                insertRestvb.setSpacing(20);
                insertRestvb.setId("CommonBackground");
                Label rnameprompt = new Label("What is the name of the restaurant?");
                TextField rnameinput = new TextField();
                Button rok = new Button("DONE");
                //insertMenuvb.getChildren().addAll(restnameprompt, itemnameinput, itemtypeprompt, itemtypeinput, imok);
                Button backaddres_admhome = new Button("Back");
                backaddres_admhome.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setScene(adminHome);
                    }
                });

                Button logoutadmhome1 = new Button("Logout");

                logoutadmhome1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setScene(loginScene);
                    }
                });

                insertRestvb.getChildren().addAll(rnameprompt, rnameinput, rok, backaddres_admhome, logoutadmhome1);

                rok.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        ConnectionClass conC = new ConnectionClass();
                        Connection con = conC.getConnection();
                        try {
                            String sql = "INSERT INTO Restaurants (REST_ID, RESTAURANT_NAME) VALUES (REST_ID_SEQ.NEXTVAL, ?)";
                            PreparedStatement pst = con.prepareStatement(sql);
                            pst.setString(1, rnameinput.getText());
                            //pst.setString(2, itemnameinput.getText());
                            ResultSet rs = pst.executeQuery();
                            pst.close();
                            con.close();
                            conC.closeConnection();
                            primaryStage.setScene(adminHome);
                            //System.out.println(uname);
                        } catch (SQLException e) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Insert Error");
                            alert.setHeaderText("Insert Error");
                            alert.setContentText("Make sure if the restaurant already exists!");
                            alert.showAndWait();

                            e.printStackTrace();
                        }
                    }
                });

                insertRestScene = new Scene(insertRestvb, 500, 500);
                insertRestScene.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());
                primaryStage.setScene(insertRestScene);
            }
        });

        addLoc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Scene insertLocScene;
                VBox insertLocvb = new VBox();
                insertLocvb.setAlignment(Pos.CENTER);
                insertLocvb.setSpacing(20);
                insertLocvb.setId("CommonBackground");
                Label lnameprompt = new Label("Where is the location?");
                TextField lnameinput = new TextField();
                Button lok = new Button("DONE");
                Button backaddloc_admhome = new Button("Back");
                backaddloc_admhome.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setScene(adminHome);
                    }
                });

                Button logoutadmhome2 = new Button("Logout");

                logoutadmhome2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setScene(loginScene);
                    }
                });

                //insertMenuvb.getChildren().addAll(restnameprompt, itemnameinput, itemtypeprompt, itemtypeinput, imok);
                insertLocvb.getChildren().addAll(lnameprompt, lnameinput, lok, backaddloc_admhome, logoutadmhome2);

                lok.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        ConnectionClass conC = new ConnectionClass();
                        Connection con = conC.getConnection();
                        try {
                            String sql = "INSERT INTO LOCATIONS (LOCATION_ID, LOCATION_NAME) VALUES (LOCATION_ID_SEQ.NEXTVAL, ?)";
                            PreparedStatement pst = con.prepareStatement(sql);
                            pst.setString(1, lnameinput.getText());
                            //pst.setString(2, itemnameinput.getText());
                            ResultSet rs = pst.executeQuery();
                            pst.close();
                            con.close();
                            conC.closeConnection();
                            primaryStage.setScene(adminHome);
                            //System.out.println(uname);
                        } catch (SQLException e) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Insert Error");
                            alert.setHeaderText("Insert Error");
                            alert.setContentText("Make sure if the location already exists!");
                            alert.showAndWait();

                            e.printStackTrace();
                        }
                    }
                });

                insertLocScene = new Scene(insertLocvb, 500, 500);
                insertLocScene.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());
                primaryStage.setScene(insertLocScene);
            }
        });

        RateUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Scene rateUserScene;
                VBox rateUservb = new VBox();
                rateUservb.setAlignment(Pos.CENTER);
                rateUservb.setSpacing(20);
                rateUservb.setId("CommonBackground");
                Label unameprompt = new Label("What is the username of the customer?");
                TextField unameinput = new TextField();
                ChoiceBox c = new ChoiceBox();
                c.setId("CommonChoicebox");
                c.getItems().addAll("10", "9", "8", "7", "6", "5", "4", "3", "2", "1", "0");
                c.setValue("10");

                Button backrateuser_admhome = new Button("Back");

                backrateuser_admhome.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setScene(adminHome);
                    }
                });

                Button logoutrateuser = new Button("Logout");

                logoutrateuser.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setScene(loginScene);
                    }
                });

                Button uok = new Button("DONE");
                //insertMenuvb.getChildren().addAll(restnameprompt, itemnameinput, itemtypeprompt, itemtypeinput, imok);
                rateUservb.getChildren().addAll(unameprompt, unameinput, c, uok, backrateuser_admhome, logoutrateuser);

                uok.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        ConnectionClass conC = new ConnectionClass();
                        Connection con = conC.getConnection();
                        try {
                            String sql = "update users set user_score = ? where username = ?";
                            PreparedStatement pst = con.prepareStatement(sql);
                            pst.setInt(1, Integer.parseInt((String) c.getValue()));
                            pst.setString(2, unameinput.getText());
                            //pst.setString(2, itemnameinput.getText());
                            ResultSet rs = pst.executeQuery();
                            pst.close();
                            con.close();
                            conC.closeConnection();
                            primaryStage.setScene(adminHome);
                            //System.out.println(uname);
                        } catch (SQLException e) {

                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Insert Error");
                            alert.setHeaderText("Insert Error");
                            alert.setContentText("Make sure you've filled all the boxes!");
                            alert.showAndWait();

                            e.printStackTrace();
                        }
                    }
                });

                rateUserScene = new Scene(rateUservb, 500, 500);
                rateUserScene.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());
                primaryStage.setScene(rateUserScene);
            }
        });

        AddBranch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Scene insertBranchScene;
                VBox insertBranchvb = new VBox();
                insertBranchvb.setAlignment(Pos.CENTER);
                insertBranchvb.setSpacing(10);
                insertBranchvb.setId("CommonBackground");

                ConnectionClass conC = new ConnectionClass();
                Connection con = conC.getConnection();
                List listRest = new ArrayList();
                List listLoc = new ArrayList();
                try {
                    String sql = "SELECT RESTAURANT_NAME FROM RESTAURANTS";

                    PreparedStatement pst = con.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        listRest.add(rs.getString(1));
                    }
                    sql = "SELECT LOCATION_NAME FROM LOCATIONS";
                    pst = con.prepareStatement(sql);
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        listLoc.add(rs.getString(1));
                    }
                    pst.close();
                    con.close();
                    conC.closeConnection();

                    //System.out.println(uname);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                ChoiceBox admListRestaurants = new ChoiceBox();
                admListRestaurants.setId("CommonChoicebox");
                admListRestaurants.getItems().addAll(listRest);
                admListRestaurants.setValue(listRest.get(0));
                ChoiceBox admListLocs = new ChoiceBox();
                admListLocs.setId("CommonChoicebox");
                admListLocs.getItems().addAll(listLoc);
                admListLocs.setValue(listLoc.get(0));
                Button admAddBranch = new Button("Confirm Restaurant!");
                TextField mngrname = new TextField();
                mngrname.setPromptText("MANAGER_NAME");
                TextField adrs = new TextField();
                adrs.setPromptText("Address");
                TextField num = new TextField();
                num.setPromptText("Contact Number");
                TextField opentime = new TextField();
                opentime.setPromptText("Open Time");
                TextField closetime = new TextField();
                closetime.setPromptText("Close Time");
                ChoiceBox holiday = new ChoiceBox();
                holiday.setId("CommonChoicebox");
                holiday.getItems().addAll("FRIDAY", "SATURDAY", "SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY");
                holiday.setValue("FRIDAY");

                Button backinsertbranch_admhome = new Button("Back");
                backinsertbranch_admhome.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setScene(adminHome);
                    }
                });

                Button logoutinsertbranch = new Button("Logout");

                logoutinsertbranch.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setScene(loginScene);
                    }
                });

                Text selectrestprompt = new Text("Select a Restaurant: ");
                Text selectlocprompt = new Text("Select a Location: ");
                Text mngrnameprompt = new Text("Enter Manager Name (First Name + Last Name): ");
                Text addressprompt = new Text("Enter the Address of this Branch: ");
                Text contact_noprompt = new Text("Enter Contact Number: ");
                Text opentimeprompt = new Text("What time does this restaurant open? ");
                Text closetimeprompt = new Text("What time does it close? ");
                Text weeklyholidayprompt = new Text("Select Weekly Holiday:  ");

                //holiday.setPrefSize(250, 40);
                //TextField holiday = new TextField();
                //holiday.setPromptText("Weekly Holiday");
                insertBranchvb.getChildren().addAll(selectrestprompt, admListRestaurants, selectlocprompt, admListLocs, mngrnameprompt, mngrname, addressprompt, adrs, contact_noprompt, num, opentimeprompt, opentime, closetimeprompt, closetime, weeklyholidayprompt, holiday, admAddBranch, backinsertbranch_admhome, logoutinsertbranch);

                insertBranchScene = new Scene(insertBranchvb, 700, 700);
                insertBranchScene.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());

                admAddBranch.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        int restid = 0, locid = 0;
                        ConnectionClass conC = new ConnectionClass();
                        Connection con = conC.getConnection();
                        try {
                            String sql = "select rest_id from restaurants where RESTAURANT_NAME = ?";
                            PreparedStatement pst = con.prepareStatement(sql);
                            pst.setString(1, (String) admListRestaurants.getValue());
                            ResultSet rs = pst.executeQuery();
                            while (rs.next()) {
                                restid = rs.getInt(1);
                            }

                            sql = "select location_id from locations where location_name = ?";
                            pst = con.prepareStatement(sql);
                            pst.setString(1, (String) admListLocs.getValue());
                            rs = pst.executeQuery();
                            while (rs.next()) {
                                locid = rs.getInt(1);
                            }

                            pst.close();
                            CallableStatement ins = con.prepareCall("{?= call SET_REST_BRANCHES(?,?,?,?,?,?,?,?)}");

                            ins.setString(2, (String) admListRestaurants.getValue());
                            ins.setString(3, (String) admListLocs.getValue());
                            ins.setString(4, mngrname.getText());
                            ins.setString(5, adrs.getText());
                            ins.setString(6, num.getText());
                            ins.setString(7, opentime.getText());
                            ins.setString(8, closetime.getText());
                            ins.setString(9, (String) holiday.getValue());
                            ins.registerOutParameter(1, Types.INTEGER);
                            ins.execute();
                            System.out.println(ins.getInt(1));

                            if (ins.getInt(1) != 1) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Insert Error");
                                alert.setHeaderText("Insert Error");
                                alert.setContentText("Make sure the branch is not already registered or you've left some fields blank!");
                                alert.showAndWait();
                            }

                            ins.close();
                            con.close();
                            conC.closeConnection();

                            primaryStage.setScene(adminHome);

                            //System.out.println(uname);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        //INSERT INTO RESTAURANTS_BRANCHES (REST_ID, LOCATION_ID, MANAGER_NAME, ADDRESS, CONTACT_NUMBER, OPEN_TIME, CLOSE_TIME, WEEKLY_HOLIDAY) VALUES (?, ?, ?, ?, ?, ?, ?, ?)    

                    }
                });

                primaryStage.setScene(insertBranchScene);
            }
        });

        AlcAuth.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Scene insertAuthScene;
                VBox insertBranchvb = new VBox();
                insertBranchvb.setAlignment(Pos.CENTER);
                insertBranchvb.setSpacing(10);
                insertBranchvb.setId("CommonBackground");

                ConnectionClass conC = new ConnectionClass();
                Connection con = conC.getConnection();
                List listRest = new ArrayList();
                List listLoc = new ArrayList();
                try {
                    String sql = "SELECT RESTAURANT_NAME FROM RESTAURANTS";

                    PreparedStatement pst = con.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        listRest.add(rs.getString(1));
                    }
                    sql = "SELECT LOCATION_NAME FROM LOCATIONS";
                    pst = con.prepareStatement(sql);
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        listLoc.add(rs.getString(1));
                    }
                    pst.close();
                    con.close();
                    conC.closeConnection();

                    //System.out.println(uname);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                ChoiceBox admListRestaurants = new ChoiceBox();
                admListRestaurants.setId("CommonChoicebox");
                admListRestaurants.getItems().addAll(listRest);
                admListRestaurants.setValue(listRest.get(0));
                ChoiceBox admListLocs = new ChoiceBox();
                admListLocs.setId("CommonChoicebox");
                admListLocs.getItems().addAll(listLoc);
                admListLocs.setValue(listLoc.get(0));
                Button admAddAu = new Button("Confirm Restaurant!");
                TextField auname = new TextField();
                auname.setPromptText("USER_NAME");
                TextField email = new TextField();
                email.setPromptText("Email ID");
                TextField comname = new TextField();
                comname.setPromptText("Committee Name");
                TextField pass = new TextField();
                pass.setPromptText("Password");

                Button backinsertbranch_admhome = new Button("Back");
                backinsertbranch_admhome.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setScene(adminHome);
                    }
                });

                Button logoutinsertbranch = new Button("Logout");

                logoutinsertbranch.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setScene(loginScene);
                    }
                });

                Text selectrestprompt = new Text("Select a Restaurant: ");
                Text selectlocprompt = new Text("Select a Location: ");
                Text aunameprompt = new Text("Enter Authority User_Name: ");
                Text emailprompt = new Text("Enter Email address: ");
                Text comprompt = new Text("Enter Committee Name: ");
                Text passprompt = new Text("Enter Password ");

                //holiday.setPrefSize(250, 40);
                //TextField holiday = new TextField();
                //holiday.setPromptText("Weekly Holiday");
                insertBranchvb.getChildren().addAll(selectrestprompt, admListRestaurants, selectlocprompt, admListLocs, aunameprompt, auname, emailprompt, email, comprompt, comname, passprompt, pass, admAddAu, backinsertbranch_admhome, logoutinsertbranch);

                insertAuthScene = new Scene(insertBranchvb, 700, 700);
                insertAuthScene.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());

                admAddAu.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        int restid = 0, locid = 0;
                        ConnectionClass conC = new ConnectionClass();
                        Connection con = conC.getConnection();
                        try {
                            String sql = "select rest_id from restaurants where RESTAURANT_NAME = ?";
                            PreparedStatement pst = con.prepareStatement(sql);
                            pst.setString(1, (String) admListRestaurants.getValue());
                            ResultSet rs = pst.executeQuery();
                            while (rs.next()) {
                                restid = rs.getInt(1);
                            }

                            sql = "select location_id from locations where location_name = ?";
                            pst = con.prepareStatement(sql);
                            pst.setString(1, (String) admListLocs.getValue());
                            rs = pst.executeQuery();
                            while (rs.next()) {
                                locid = rs.getInt(1);
                            }

                            pst.close();
                            CallableStatement ins = con.prepareCall("{?= call SET_BRANCHES_AUTH(?,?,?,?,?,?)}");

                            ins.setString(2, (String) admListRestaurants.getValue());
                            ins.setString(3, (String) admListLocs.getValue());
                            ins.setString(4, auname.getText());
                            ins.setString(5, email.getText());
                            ins.setString(6, comname.getText());
                            ins.setString(7, pass.getText());

                            ins.registerOutParameter(1, Types.INTEGER);
                            ins.execute();
                            System.out.println(ins.getInt(1));

                            if (ins.getInt(1) == 101) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Insert Error");
                                alert.setHeaderText("Insert Error");
                                alert.setContentText("Make sure the branch is registered!");
                                alert.showAndWait();

                            }

                            if (ins.getInt(1) != 1 && ins.getInt(1) != 101) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Insert Error");
                                alert.setHeaderText("Insert Error");
                                alert.setContentText("Make sure everything is correctly inserted!");
                                alert.showAndWait();
                            }

                            ins.close();
                            con.close();
                            conC.closeConnection();
                            primaryStage.setScene(adminHome);

                            //System.out.println(uname);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        //INSERT INTO RESTAURANTS_BRANCHES (REST_ID, LOCATION_ID, MANAGER_NAME, ADDRESS, CONTACT_NUMBER, OPEN_TIME, CLOSE_TIME, WEEKLY_HOLIDAY) VALUES (?, ?, ?, ?, ?, ?, ?, ?)    

                    }
                });

                primaryStage.setScene(insertAuthScene);
            }
        });

        seeReqs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ConnectionClass conC = new ConnectionClass();
                Connection con = conC.getConnection();
                ObservableList<Requests> offersos = FXCollections.observableArrayList();
                try {
                    String sql = "select REQ_ITEM_TYPE from REQUEST_MENU_TYPES";

                    PreparedStatement pst = con.prepareStatement(sql);

                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        String str1 = rs.getString(1);
                        offersos.add(new Requests(str1));
                    }
                    pst.close();
                    con.close();
                    conC.closeConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                TableView<Requests> Offertable;

                TableColumn<Requests, String> rest_nameColumn = new TableColumn<>("Requested_Type");
                rest_nameColumn.setMinWidth(200);
                rest_nameColumn.setCellValueFactory(new PropertyValueFactory<>("Requested_Type"));

                TableColumn actionCol = new TableColumn("Approve");
                actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

                Callback<TableColumn<Requests, String>, TableCell<Requests, String>> cellFactory
                        = //
                        new Callback<TableColumn<Requests, String>, TableCell<Requests, String>>() {

                    @Override
                    public TableCell call(final TableColumn<Requests, String> param) {
                        final TableCell<Requests, String> cell = new TableCell<Requests, String>() {

                            final Button btn = new Button("Approve");

                            @Override
                            public void updateItem(String item, boolean empty) {

                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {

                                        Requests r = getTableView().getItems().get(getIndex());

                                        String reqType = r.getRequested_Type();

                                        ConnectionClass conC = new ConnectionClass();
                                        Connection con = conC.getConnection();
                                        try {

                                            CallableStatement ins = con.prepareCall("{?= call APPROVE_MENUTYPE_REQ(?)}");

                                            ins.setString(2, reqType);

                                            ins.registerOutParameter(1, Types.INTEGER);
                                            ins.execute();
                                            System.out.println(ins.getInt(1));

                                            if (ins.getInt(1) != 1) {
                                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                                alert.setTitle("Insert Error");
                                                alert.setHeaderText("Insert Error");
                                                alert.setContentText("Make sure the request exists!");
                                                alert.showAndWait();
                                            }

                                            ins.close();
                                            con.close();
                                            conC.closeConnection();

                                            primaryStage.setScene(adminHome);

                                            //System.out.println(uname);
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }

                                        primaryStage.setScene(adminHome);

                                        // eikhane sql boshbe
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

                actionCol.setCellFactory(cellFactory);

                Offertable = new TableView<>();
                Offertable.setItems(offersos);
                Offertable.getColumns().addAll(rest_nameColumn, actionCol);

                Button goBack_ShowOffer_home = new Button("Back");
                goBack_ShowOffer_home.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setScene(adminHome);
                    }
                });

                VBox offervbox = new VBox();
                offervbox.getChildren().addAll(goBack_ShowOffer_home, Offertable);
                offervbox.setAlignment(Pos.CENTER);
                offervbox.setId("offervboxbackground");
                Scene offerScene = new Scene(offervbox, 500, 500);
                offerScene.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());

                primaryStage.setScene(offerScene);
            }
        });

        backfromtbb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(adminHome);
            }
        });

        //primaryStage.setScene(signupScene);
        //primaryStage.setTitle("Signup");
        /*liokButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                
            }
        });*/
        toli.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(loginScene);
                primaryStage.setTitle("Welcome!");
            }
        });

        liokButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                uname = usernameli.getText();
                String pass = passwordli.getText();
                System.out.println(uname + pass);
                //primaryStage.setScene(scene);
                boolean success = false;
                if ((String) usertype.getValue() == "User") {
                    success = new Users().validateLogin(uname, pass);
                } else if ((String) usertype.getValue() == "Admin") {
                    success = new Admins().validateLogin(uname, pass);
                } else if ((String) usertype.getValue() == "Authority") {
                    success = new Authorities().validateLogin(uname, pass);
                }

                if (success && (String) usertype.getValue() == "User") {
                    // successful login
                    try {
                        Text welcomeUserText = new Text("Welcome " + uname + "!");
                        welcomeUserText.setFont(Font.font("Verdana", 20));
                        // welcomeUserLabel.setMinSize(50, 50);
                        welcomeUserText.setId("WelcomeUserText");
                        hsGridPane.add(welcomeUserText, 5, 3);

                        primaryStage.setScene(HomeScene); // ei jaygay homeScene ashbe
                        primaryStage.setTitle("Home");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if (success && (String) usertype.getValue() == "Admin") {
                    //System.out.println("Admin Logged in");
                    try {

                        welcomeAdminText.setText("Welcome " + uname + "!");

                        welcomeAdminText.setFont(Font.font("Verdana", 20));
                        // welcomeUserLabel.setMinSize(50, 50);
                        welcomeAdminText.setId("WelcomeUserText");

                        primaryStage.setScene(adminHome);
                        primaryStage.setTitle("Home");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if (success && (String) usertype.getValue() == "Authority") {
                    //System.out.println("Admin Logged in");
                    try {
                        welcomeAuthText.setText("Welcome " + uname + "!");
                        welcomeAuthText.setFont(Font.font("Verdana", 20));
                        // welcomeUserLabel.setMinSize(50, 50);
                        welcomeAuthText.setId("WelcomeUserText");

                        primaryStage.setScene(authorityHome);
                        primaryStage.setTitle("Home");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    // failed login
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Incorrect Credentials");
                    alert.setHeaderText("Incorrect Credentials");
                    alert.setContentText("The username and password you provided is not correct.");
                    alert.showAndWait();
                }

            }
        });

        toSignup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(signupScene);
                primaryStage.setTitle("Sign UP");
            }
        });

        Scene suSuccess;
        Label signedUp = new Label("Congrats! Sign Up Successful!");
        Button toWelcomeScene = new Button("Log in");
        VBox sus = new VBox();
        sus.setAlignment(Pos.CENTER);
        sus.setId("CommonBackground");
        sus.setSpacing(20);
        sus.getChildren().addAll(signedUp, toWelcomeScene);
        suSuccess = new Scene(sus, 500, 500);
        suSuccess.getStylesheets().add(TestProjectDB.class.getResource("CSSButtons1.css").toExternalForm());
        toWelcomeScene.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(loginScene);
                primaryStage.setTitle("Welcome!");
            }
        });

        suokButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String fname, lname, email, number, gender, password;
                uname = usernameInput.getText();
                fname = FirstNameInput.getText();
                lname = LastNameInput.getText();
                email = emailInput.getText();
                number = PhoneInput.getText();
                gender = (String) genderbox.getValue();
                password = pb.getText();

                //primaryStage.setScene(scene);
                ConnectionClass conC = new ConnectionClass();
                Connection con = conC.getConnection();

                try {

                    String sql = "INSERT into users (user_id, username, email, FIRSTNAME, LASTNAME, PASSWORD, USER_NUMBER, GENDER) values (user_id_seq.nextval, ? , ? , ? , ? , ? , ? , ?)";

                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, uname);
                    pst.setString(2, email);
                    pst.setString(3, fname);
                    pst.setString(4, lname);
                    pst.setString(5, password);
                    pst.setString(6, number);
                    pst.setString(7, gender);

                    ResultSet rs = pst.executeQuery();

                    pst.close();
                    con.close();
                    conC.closeConnection();

                    //System.out.println(uname);
                    primaryStage.setScene(suSuccess);

                } catch (SQLException e) {
                    System.out.println("Invalid Sign Up!");
                    //e.printStackTrace();
                    //System.out.println("Not printed");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Sign Up");
                    alert.setHeaderText("Invalid Sign Up");
                    alert.setContentText("Please fill all the fields or try a different username!");
                    alert.showAndWait();
                }

            }

        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
