package model;

import java.util.Vector;

public class Supervisor extends Jabatan{
	public static int counter = 0;
	private static final double bonusRaise = 7.5;
	
	public Supervisor(String karyawanID, String namaKaryawan, String jenisKelamin, String jabatanKaryawan) {
		super(karyawanID, namaKaryawan, jenisKelamin, jabatanKaryawan, 6000000);
		// TODO Auto-generated constructor stub
	}

	public static void evaluateBonus(Vector<Jabatan> karyawanMusang) {
		if (counter % 3 == 1 && counter != 1) {
			int c = 0;
			String text = "Bonus sebesar " + bonusRaise + "% telah diberikan kepada karyawan dengan id ";
			for (int i = 0; i < karyawanMusang.size(); i++) {
				if (karyawanMusang.get(i) instanceof Supervisor) {
					karyawanMusang.get(i).raiseSalary(bonusRaise);
					
					text += karyawanMusang.get(i).getKaryawanID() + ", ";
					
					c++;
					if (c == (counter-1)) {
						break;
					}
				}
			}
			
			System.out.println(text.substring(0, text.length()-2));
		}
	}
}
