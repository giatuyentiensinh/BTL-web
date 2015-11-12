# Bài tập lớn công nghệ web tiên tiến
## Đề tài xây dựng web bán các sản phẩm điện thoại di động

# server side: `Play Framework - Java`
# client side: `Angular JS`
# database: `Mongodb`

## Import data

Sử dụng mongodb bản sách tay.

``` command
# Bật server
$ sudo ./mongod -replSet rs0

# Import dữ liệu 
$ ./mongoimport --db btlweb --collection phone --file <path đến file data.json>

```