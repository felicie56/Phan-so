import kotlin.math.abs

// lop PhanSo gom tu va mau
class PhanSo(var tu: Int, var mau: Int) {

    // nhap phan so tu ban phim
    fun nhap() {
        while (true) {
            print("Nhap tu so: ")
            tu = readln().toInt()
            print("Nhap mau so: ")
            mau = readln().toInt()
            if (mau != 0) {
                break
            } else {
                println("Mau so phai khac 0, nhap lai")
            }
        }
    }

    // in phan so ra man hinh
    fun xuat() {
        println("$tu/$mau")
    }

    // toi gian phan so
    fun toiGian() {
        val u = ucln(abs(tu), abs(mau))
        tu /= u
        mau /= u
        // dua mau ve so duong
        if (mau < 0) {
            tu = -tu
            mau = -mau
        }
    }

    // so sanh voi 1 phan so khac
    // tra ve -1 neu nho hon, 0 neu bang, 1 neu lon hon
    fun soSanh(ps: PhanSo): Int {
        val a = tu.toLong() * ps.mau
        val b = ps.tu.toLong() * mau
        if (a < b) return -1
        if (a == b) return 0
        return 1
    }

    // cong voi 1 phan so khac
    fun cong(ps: PhanSo): PhanSo {
        val t = tu * ps.mau + ps.tu * mau
        val m = mau * ps.mau
        val kq = PhanSo(t, m)
        kq.toiGian()
        return kq
    }

    // tra ve gia tri thuc cua phan so
    fun giaTri(): Double {
        return tu.toDouble() / mau.toDouble()
    }

    // ham tinh uoc chung lon nhat
    private fun ucln(a: Int, b: Int): Int {
        if (b == 0) return a
        return ucln(b, a % b)
    }
}

fun main() {
    // nhap so luong phan so
    print("Nhap so luong phan so: ")
    val n = readln().toInt()
    val arr = Array(n) { PhanSo(1, 1) }

    // nhap danh sach phan so
    println("Nhap danh sach phan so")
    for (i in arr.indices) {
        println("Phan so thu ${i + 1}:")
        arr[i].nhap()
    }

    // in danh sach phan so vua nhap
    println("1. Danh sach phan so vua nhap")
    for (ps in arr) {
        ps.xuat()
    }

    // toi gian danh sach phan so
    println("2. Danh sach phan so toi gian")
    for (ps in arr) {
        ps.toiGian()
        ps.xuat()
    }

    // tinh tong tat ca phan so
    println("3. Tong tat ca phan so")
    var tong = arr[0]
    for (i in 1 until arr.size) {
        tong = tong.cong(arr[i])
    }
    tong.xuat()

    // tim phan so lon nhat
    println("4. Phan so lon nhat")
    var max = arr[0]
    for (ps in arr) {
        if (ps.soSanh(max) == 1) {
            max = ps
        }
    }
    max.xuat()

    // sap xep giam dan
    println("5. Sap xep giam dan")
    val arr2 = arr.sortedByDescending { it.giaTri() }
    for (ps in arr2) {
        ps.xuat()
    }
}
