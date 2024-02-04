# Transfer System

**Sistem Transfer** adalah aplikasi yang memungkinkan pengguna untuk melakukan transfer dana antar akun dan bank internal. Aplikasi ini dibangun menggunakan **Spring Boot** dan menyertakan integrasi dengan database **PostgreSQL**.

## Menjalankan Aplikasi

### Prasyarat

Pastikan Anda telah memenuhi prasyarat berikut sebelum menjalankan aplikasi:

- [Docker](https://www.docker.com/) terinstal di sistem Anda.
- Database **PostgreSQL** terhubung dan dapat diakses oleh aplikasi.
- running query create 3 tabel ada di folder `sql`

### Konfigurasi

Konfigurasikan akses database pada file `application-dev.properties` jika ingin running di local tanpa `docker`. Pastikan untuk menyediakan informasi yang benar, seperti URL database, username, dan password.

### Menjalankan Aplikasi dengan Docker

1. **Build Aplikasi**

    ```bash
    mvn clean install
    ```

2. **Build dan Jalankan Docker Container**

    ```bash
    docker-compose up --build
    ```

   - Pastikan aplikasi berhasil di-*deploy* dan dapat diakses pada [http://localhost:8080/transfer-system](http://localhost:8080/transfer-system).
   - Sesuaikan konfigurasi database yang ada di dalam file docker-compose.yaml

3. **Gunakan API**

   Gunakan Postman atau alat pengujian API lainnya untuk mengakses dan menguji endpoint-endpoint transfer.

## Endpoint API

Berikut adalah beberapa endpoint API yang dapat digunakan:

- **POST `/api/transfers`**: Inisiasi transfer dana antar akun.
    - Body Request:
        ```json
        {
          "pin": "123456",
          "senderAccountNumber": "1234567890",
          "receiverAccountNumber": "0987654321",
          "amount": 100000,
          "channel": "Mobile Banking",
          "receiverBankCode": "BCA",
          "memo": "Transfer for lunch"
        }
        ```
    - Response Sukses:
        ```json
        {
          "code": 200,
          "message": "Transfer successful",
          "data": {
            "referenceNumber": "TRF123456789",
            "transferDate": "2024-02-05T12:00:00",
            "amount": 100000
          }
        }
        ```
    - Response Gagal:
        ```json
        {
          "code": 400,
          "message": "Invalid PIN"
        }
        ```

- **GET `/api/users/{accountNumber}`**: Mendapatkan informasi pengguna berdasarkan nomor akun.
    - Response Sukses:
        ```json
        {
          "code": 200,
          "message": "User details retrieved successfully",
          "data": {
            "userId": 1,
            "nik": "1234567890123456",
            "fullName": "John Doe",
            "email": "john.doe@example.com",
            "phoneNumber": "+1234567890",
            "pin": "******",
            "accountNumber": "1234567890",
            "balance": 500000
          }
        }
        ```
    - Response Gagal:
        ```json
        {
          "code": 404,
          "message": "User not found"
        }
        ```

Pastikan untuk memahami dan mengikuti dokumentasi API untuk penggunaan yang lebih lanjut.

## Catatan

- Pastikan informasi database, seperti URL, username, dan password, telah dikonfigurasi dengan benar.
- Gunakan API dengan bijak dan sesuai dengan kebutuhan bisnis serta kebijakan keamanan yang berlaku.
- penjelasan sistem transfer ini ada di file `keterangan dan pseudocode`
