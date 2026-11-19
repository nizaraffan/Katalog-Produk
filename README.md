# Katalog Produk – UPI Store

Aplikasi Katalog Produk merupakan aplikasi desktop berbasis Java yang dikembangkan menggunakan JavaFX untuk membangun Graphical User Interface (GUI). Aplikasi ini dibuat untuk mengelola data produk dengan memanfaatkan database SQLite sebagai tempat penyimpanan data.

Aplikasi ini memungkinkan pengguna untuk menambahkan, melihat, mengedit, dan menghapus data produk melalui tampilan katalog yang interaktif.

## Fitur Aplikasi

- Menambahkan produk baru ke dalam katalog.
- Menampilkan daftar produk yang tersimpan di database.
- Menampilkan foto, nama, harga, dan ID produk.
- Mengedit data produk yang sudah tersimpan.
- Menghapus produk dari katalog.
- Konfirmasi sebelum menghapus produk.
- Menampilkan jumlah produk yang tersedia.
- Validasi input nama dan harga produk.
- Penyimpanan data produk menggunakan database SQLite.

## Teknologi yang Digunakan

| Teknologi | Keterangan |
|---|---|
| Java JDK 17 | Bahasa pemrograman |
| JavaFX 21 | Framework GUI |
| SQLite | Database |
| SQLite JDBC | Koneksi database |
| Apache Maven | Build management |
| Apache NetBeans IDE | IDE pengembangan |
| Scene Builder | Perancangan antarmuka FXML |

## Tampilan Aplikasi

Berikut merupakan beberapa tampilan dari aplikasi Katalog Produk UPI Store.

**1. Tampilan Utama**

![Tampilan Utama](docs/images/tampilan-utama.png)

**2. Tampilan Form Produk**

![Form Produk](docs/images/form-produk.png)

**3. Tampilan Katalog Produk**

![Katalog Produk](docs/images/katalog-produk.png)

**4. Tampilan Konfirmasi Hapus**

![Konfirmasi Hapus](docs/images/konfirmasi-hapus.png)

## Operasi CRUD

Aplikasi ini menerapkan konsep CRUD (Create, Read, Update, Delete), yaitu:

1. **Create (Tambah)**  
   Menambahkan data produk baru berupa nama, harga, dan foto.

2. **Read (Tampil)**  
   Menampilkan seluruh data produk yang tersimpan di database.

3. **Update (Edit)**  
   Mengubah informasi produk berdasarkan ID produk.

4. **Delete (Hapus)**  
   Menghapus data produk berdasarkan ID produk.

## Struktur Project

```text
KatalogProduk/
├── data/
│   └── katalog.db
├── src/
│   └── main/
│       ├── java/
│       │   ├── controller/
│       │   │   └── KatalogController.java
│       │   ├── data/
│       │   │   └── ProdukRepository.java
│       │   ├── db/
│       │   │   └── Database.java
│       │   ├── main/
│       │   │   └── MainApp.java
│       │   └── model/
│       │       └── Produk.java
│       └── resources/
│           ├── image/
│           │   ├── kopi.png
│           │   ├── roti.png
│           │   └── susu.png
│           └── view/
│               ├── katalog.fxml
│               └── styles/
│                   └── app.css
├── pom.xml
└── README.md
```

## Cara Menjalankan Aplikasi

### 1. Clone Repository

Buka terminal atau Command Prompt, kemudian jalankan perintah berikut:

```bash
git clone https://github.com/nizaraffan/Katalog-Produk
```

### 2. Masuk ke Direktori Project

```bash
cd Katalog-Produk
```

### 3. Periksa Instalasi Java dan Maven

Pastikan Java dan Maven sudah terpasang pada komputer.

```bash
java -version
mvn -version
```

### 4. Buka Project

Buka Apache NetBeans, kemudian pilih:

**File → Open Project**

Cari dan pilih folder project `Katalog-Produk`.

### 5. Jalankan Aplikasi

Buka terminal pada direktori utama project, kemudian jalankan:

```bash
mvn clean javafx:run
```

Tunggu hingga proses build selesai. Jika berhasil, aplikasi Katalog Produk UPI Store akan terbuka.

## Cara Menggunakan Aplikasi

1. Jalankan aplikasi Katalog Produk UPI Store.
2. Masukkan nama produk pada kolom yang tersedia.
3. Masukkan harga produk.
4. Pilih foto produk yang tersedia.
5. Klik tombol **Simpan** untuk menambahkan produk.
6. Produk yang berhasil disimpan akan muncul pada katalog.
7. Klik tombol **Edit** pada produk untuk mengubah data produk.
8. Ubah informasi produk melalui form, kemudian klik **Simpan** untuk memperbarui data.
9. Klik tombol **Hapus** untuk menghapus produk.
10. Konfirmasi penghapusan produk melalui dialog yang muncul.

