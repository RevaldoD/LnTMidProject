package model;

import java.util.Vector;

public abstract class Jabatan {
	private String karyawanID, namaKaryawan, jenisKelamin, jabatanKaryawan;
	private int gajiKaryawan;
	
	public Jabatan(String karyawanID, String namaKaryawan, String jenisKelamin, String jabatanKaryawan,
			int gajiKaryawan) {
		super();
		this.karyawanID = karyawanID;
		this.namaKaryawan = namaKaryawan;
		this.jenisKelamin = jenisKelamin;
		this.jabatanKaryawan = jabatanKaryawan;
		this.gajiKaryawan = gajiKaryawan;
	}

	public String getKaryawanID() {
		return karyawanID;
	}

	public void setKaryawanID(String karyawanID) {
		this.karyawanID = karyawanID;
	}

	public String getNamaKaryawan() {
		return namaKaryawan;
	}
	public void setNamaKaryawan(String namaKaryawan) {
		this.namaKaryawan = namaKaryawan;
	}
	public String getJenisKelamin() {
		return jenisKelamin;
	}
	public void setJenisKelamin(String jenisKelamin) {
		this.jenisKelamin = jenisKelamin;
	}
	public String getJabatanKaryawan() {
		return jabatanKaryawan;
	}
	public void setJabatanKaryawan(String jabatanKaryawan) {
		this.jabatanKaryawan = jabatanKaryawan;
	}
	
	public int getGajiKaryawan() {
		return gajiKaryawan;
	}

	public void setGajiKaryawan(int gajiKaryawan) {
		this.gajiKaryawan = gajiKaryawan;
	}

	public void raiseSalary(double percentage) {
		gajiKaryawan *= (100 + percentage)/100;
//		gajiKaryawan += (percentage/100) * gajiKaryawan;
	}

}
