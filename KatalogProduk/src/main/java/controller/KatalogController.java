package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import data.ProdukRepository;
import model.Produk;

public class KatalogController {

    @FXML
    private TextField namaField;

    @FXML
    private TextField hargaField;

    @FXML
    private ComboBox<String> fotoBox;

    @FXML
    private Label pesanLabel;

    @FXML
    private Label jumlahLabel;

    @FXML
    private TilePane katalogPane;

    private final ProdukRepository repository = new ProdukRepository();

    private long selectedProductId = -1;

    @FXML
    private void initialize() {
        fotoBox.getItems().add("roti.png");
        fotoBox.getItems().add("susu.png");
        fotoBox.getItems().add("kopi.png");

        fotoBox.setValue("roti.png");

        try {
            showData();
        } catch (SQLException e) {
            pesanLabel.setText("Gagal membaca database. Lihat Output.");
            e.printStackTrace();
        }
    }

    private void showData() throws SQLException {
        ArrayList<Produk> daftar = repository.getAll();

        katalogPane.getChildren().clear();

        for (Produk produk : daftar) {
            VBox kartu = createProductCard(produk);
            katalogPane.getChildren().add(kartu);
        }

        jumlahLabel.setText("Jumlah produk: " + daftar.size());
    }

    private VBox createProductCard(Produk produk) {
        ImageView fotoView = new ImageView();

        fotoView.setFitWidth(170);
        fotoView.setFitHeight(110);
        fotoView.setPreserveRatio(true);

        StackPane bingkai = new StackPane();

        bingkai.setPrefSize(180, 120);
        bingkai.setMinSize(180, 120);
        bingkai.setMaxSize(180, 120);

        URL lokasi = getClass().getResource("/image/" + produk.getFoto());

        if (lokasi != null) {
            Image gambar = new Image(
                lokasi.toExternalForm(), 170, 110, true, true
            );

            if (!gambar.isError()) {
                fotoView.setImage(gambar);
                bingkai.getChildren().add(fotoView);
            } else {
                bingkai.getChildren().add(
                    new Label("Gambar tidak terbaca")
                );
            }
        } else {
            bingkai.getChildren().add(
                new Label("Gambar tidak tersedia")
            );
        }

        Label namaLabel = new Label(produk.getNama());
        namaLabel.setWrapText(true);
        namaLabel.getStyleClass().add("nama-produk");

        Label hargaLabel = new Label("Rp" + produk.getHarga());

        Label idLabel = new Label("ID: " + produk.getId());
        idLabel.getStyleClass().add("muted");

        VBox kartu = new VBox();

        kartu.setSpacing(8);
        kartu.setMinWidth(220);
        kartu.setPrefWidth(220);
        kartu.setMaxWidth(220);

        kartu.getStyleClass().add("kartu-produk");

        kartu.getChildren().addAll(
            bingkai, namaLabel, hargaLabel, idLabel
        );

        
        Button editButton = new Button("Edit");
        Button hapusButton = new Button("Hapus");

        editButton.setOnAction(event -> editProduct(produk));
        hapusButton.setOnAction(event -> hapusProduk(produk));

        kartu.getChildren().addAll(editButton, hapusButton);

        return kartu;
    }

    @FXML
    private void saveProduct() {
        try {
            String nama = namaField.getText();
            long harga = Long.parseLong(hargaField.getText().trim());
            String foto = fotoBox.getValue();

            Produk produk;

            if (selectedProductId != -1) {

                produk = new Produk(
                    selectedProductId, nama, harga, foto
                );

                repository.update(produk);

                pesanLabel.setText("Produk berhasil diperbarui.");

                selectedProductId = -1;

            } else {

                produk = new Produk(nama, harga, foto);

                repository.add(produk);

                pesanLabel.setText("Produk berhasil disimpan.");
            }

            namaField.clear();
            hargaField.clear();
            fotoBox.setValue("roti.png");

            showData();

        } catch (NumberFormatException e) {
            pesanLabel.setText("Harga harus berupa angka.");

        } catch (IllegalArgumentException e) {
            pesanLabel.setText(e.getMessage());

        } catch (SQLException e) {
            pesanLabel.setText("Gagal menyimpan perubahan.");
            e.printStackTrace();
        }
    }

    private void editProduct(Produk produk) {
        selectedProductId = produk.getId();

        namaField.setText(produk.getNama());
        hargaField.setText(String.valueOf(produk.getHarga()));
        fotoBox.setValue(produk.getFoto());

        pesanLabel.setText("Silakan ubah data produk.");
    }

    private void hapusProduk(Produk produk) {
        Alert konfirmasi = new Alert(Alert.AlertType.CONFIRMATION);

        konfirmasi.setTitle("Konfirmasi Hapus");
        konfirmasi.setHeaderText("Hapus Produk");

        konfirmasi.setContentText(
            "Apakah kamu yakin ingin menghapus "
            + produk.getNama() + "?"
        );

        if (konfirmasi.showAndWait().orElse(ButtonType.CANCEL)
                == ButtonType.OK) {

            try {
                repository.delete(produk.getId());

                showData();

                pesanLabel.setText("Produk berhasil dihapus.");

            } catch (SQLException e) {
                pesanLabel.setText("Gagal menghapus produk.");
                e.printStackTrace();
            }
        }
    }
}
