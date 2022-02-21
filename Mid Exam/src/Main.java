import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import model.Admin;
import model.Jabatan;
import model.Manager;
import model.Supervisor;

public class Main {
	Scanner scan = new Scanner(System.in);
	Vector<Jabatan> karyawanMusang = new Vector<>();

	public void cls() {
		for (int i = 0; i < 40; i++) {
			System.out.println();
		}
	}

	private int TryCatchNum() {
		int number = 0;

		try {
			number = scan.nextInt();
			scan.nextLine();
		} catch (Exception e) {
			System.out.println("Input must be Integer number!");
			scan.nextLine();
		}
		return number;
	}

	private void Enter() {
		System.out.print("ENTER to return");
		scan.nextLine();
	}

	public Main() {
		int choose;

		do {
			cls();
			System.out.println("PT Musang");
			System.out.println("=================");
			System.out.println("1. Insert Data Karyawan");
			System.out.println("2. View Data Karyawan");
			System.out.println("3. Update Data Karyawan");
			System.out.println("4. Delete Data Karyawan");
			System.out.println("5. Exit");
			System.out.print(">>> ");
			choose = TryCatchNum();

			switch (choose) {
			case 1:
				Insert();
				Enter();
				break;
			case 2:
				cls();
				if (karyawanMusang.isEmpty()) {
					System.out.println("No menu available");
				} else {
					View();
				}
				Enter();
				break;
			case 3:
				cls();
				if (karyawanMusang.isEmpty()) {
					System.out.println("No menu available");
				} else {
					Update();
				}
				Enter();
				break;
			case 4:
				cls();
				if (karyawanMusang.isEmpty()) {
					System.out.println("No menu available");
				} else {
					Delete();
				}
				Enter();
				break;
			case 5:
				cls();
				System.out.println("Thanks for using this program!");
				System.exit(0);
				break;
			}
		} while (choose != 5);
	}

	private String generateID() {
		String karyawanID = "";
		boolean isDuplicate = false;
		do {
			Random rand = new Random();
			int Number = rand.nextInt(9999);
			char c1 = (char) ('A' + rand.nextInt(26));
			char c2 = (char) ('A' + rand.nextInt(26));

			karyawanID = String.format("%s%s-%d", c1,c2,Number);
			
			for (int i = 0; i < karyawanMusang.size(); i++) {
				if (karyawanMusang.get(i).getKaryawanID().equals(karyawanID)) {
					isDuplicate = true;
					break;
				}
			}	
		} while(isDuplicate);
		return karyawanID;
	}

	private void Insert() {
		cls();
		String namaKaryawan, jenisKelamin, jabatan;
		String karyawanID = "";
		do {
			System.out.print("Input nama karywan [>=3]: ");
			namaKaryawan = scan.nextLine();
		} while (namaKaryawan.length() < 3);

		do {
			System.out.print("Input jenis kelamin [Laki-laki | Perempuan][Case Sensitive]: ");
			jenisKelamin = scan.nextLine();
		} while (!jenisKelamin.equals("Laki-laki") && !jenisKelamin.equals("Perempuan"));

		do {
			System.out.print("Input menu type [Manager | Supervisor | Admin][Case Sensitive]: ");
			jabatan = scan.nextLine();
		} while (!jabatan.equals("Manager") && !jabatan.equals("Supervisor") && !jabatan.equals("Admin"));

		karyawanID = generateID();
		System.out.println("Berhasil menambahkan karyawan dengan id " + karyawanID);
		
		if (jabatan.equals("Manager")) {
			karyawanMusang.add(new Manager(karyawanID, namaKaryawan, jenisKelamin, jabatan));
			Manager.counter++;
			Manager.evaluateBonus(karyawanMusang);
		} else if (jabatan.equals("Supervisor")) {
			karyawanMusang.add(new Supervisor(karyawanID, namaKaryawan, jenisKelamin, jabatan));
			Supervisor.counter++;
			Supervisor.evaluateBonus(karyawanMusang);
		} else if (jabatan.equals("Admin")) {
			karyawanMusang.add(new Admin(karyawanID, namaKaryawan, jenisKelamin, jabatan));
			Admin.counter++;
			Admin.evaluateBonus(karyawanMusang);
		}
		
	}

	private void View() {
		Jabatan jb = null;
		int index = 1;
			for (int i = 0; i < karyawanMusang.size(); i++) {
				for (int j = 1; j < karyawanMusang.size(); j++) {
					if (karyawanMusang.get(j).getNamaKaryawan().compareTo(karyawanMusang.get(i).getNamaKaryawan()) < 0) {
						Jabatan tempName = karyawanMusang.get(j);
						karyawanMusang.set(j, karyawanMusang.get(i));
						karyawanMusang.set(i, tempName);
					}
				}
		}
		System.out.println("|------|---------------------|--------------------------|---------------------|----------------|---------------------|");//jumlah garis = jumlah spasi + 1
		System.out.printf("|%-5s |%-20s |%-25s |%-20s |%-15s |%-20s |\n", "No", "Kode Karyawan", "Nama Karyawan", "Jenis Kelamin", "Jabatan", "Gaji Karyawan");
		System.out.println("|------|---------------------|--------------------------|---------------------|----------------|---------------------|");
		for (Jabatan jabatan : karyawanMusang) {
			System.out.printf("|%5d |%20s |%25s |%20s |%15s |%20d | \n", index, jabatan.getKaryawanID(), jabatan.getNamaKaryawan(), jabatan.getJenisKelamin(), jabatan.getJabatanKaryawan(), jabatan.getGajiKaryawan());
			index++;
		}
		System.out.println("|------|---------------------|--------------------------|---------------------|----------------|---------------------|");
	}

	private void Update() {
		Jabatan jb;
		String nama = "", jenisKelamin = "", jabatan = "", karyawanID = "";
		int update = 1;
		if (karyawanMusang.isEmpty()) {
			System.out.println("No menu available");
		} else {
			View();
			do {
				System.out.print("Input nomor urutan karyawan yang ingin diupdate: ");
				update = TryCatchNum();
			} while (update < 1 || update > karyawanMusang.size());

			jb = karyawanMusang.get(update - 1);

			do {
				System.out.print("Input nama karywan [>=3]: ");
				nama = scan.nextLine();
				if (nama.equals("0")) {
					nama = karyawanMusang.get(update - 1).getNamaKaryawan();
					break;
				}
			} while (nama.length() < 3);

			jb.setNamaKaryawan(nama);

			do {
				System.out.print("Input jenis kelamin [Laki-Laki | Perempuan][Case Sensitive]: ");
				jenisKelamin = scan.nextLine();
				if (jenisKelamin.equals("0")) {
					jenisKelamin = karyawanMusang.get(update - 1).getJenisKelamin();
					break;
				}
			} while (!jenisKelamin.equals("Laki-Laki") && !jenisKelamin.equals("Perempuan"));

			jb.setJenisKelamin(jenisKelamin);

			do {
				System.out.print("Input menu type [Manager | Supervisor | Admin][Case Sensitive]: ");
				jabatan = scan.nextLine();
				if (jabatan.equals("0")) {
					jabatan = karyawanMusang.get(update - 1).getJabatanKaryawan();
					break;
				}
			} while (!jabatan.equals("Manager") && !jabatan.equals("Supervisor") && !jabatan.equals("Admin"));
			
			if (!jabatan.equals(jb.getJabatanKaryawan())) {
				if(jb instanceof Manager) {
					Manager.counter--;
				}
				else if(jb instanceof Supervisor) {
					Supervisor.counter--;
				}
				else if(jb instanceof Admin) {
					Admin.counter--;
				}
				
				if (jabatan.equals("Manager")) {
					karyawanMusang.set(update-1, new Manager(jb.getKaryawanID(), nama, jenisKelamin, jabatan));
					Manager.counter++;
					Manager.evaluateBonus(karyawanMusang);
				} else if (jabatan.equals("Supervisor")) {
					karyawanMusang.set(update-1, new Supervisor(jb.getKaryawanID(), nama, jenisKelamin, jabatan));
					Supervisor.counter++;
					Supervisor.evaluateBonus(karyawanMusang);
				} else if (jabatan.equals("Admin")) {
					karyawanMusang.set(update-1, new Admin(jb.getKaryawanID(), nama, jenisKelamin, jabatan));
					Admin.counter++;
					Admin.evaluateBonus(karyawanMusang);
				}
			}
			System.out.println("Berhasil mengupdate karyawan dengan id " + karyawanMusang.get(update - 1).getKaryawanID());
		}
	}

	private void Delete() {
		int remove;
		View();
		do {
			System.out.print("Input nomor karyawan yang ingin dihapus: ");
			remove = TryCatchNum();
		} while (remove < 1 || remove > karyawanMusang.size());

		System.out.printf("Karyawan dengan kode %s berhasil dihapus \n",
				karyawanMusang.get(remove - 1).getKaryawanID());
		karyawanMusang.remove(remove - 1);
	}

	public static void main(String[] args) {
		new Main();
	}

}
