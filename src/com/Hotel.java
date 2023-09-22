package com;

import java.util.ArrayList;
import java.util.Scanner;
import com.classes.Employee;
import com.classes.KitchenStaff;
import com.classes.ReceptionStaff;
import com.classes.Room;
import com.classes.AdmnistrationStaff;
import com.classes.CleaningStaff;
import com.classes.Cliente;

public class Hotel {
    Scanner scanner = new Scanner(System.in);
    String linha = "========================================================================";
    private static int count = 0;
    private int id = 1;

    // Iniciando uma lista de empregados.
    private ArrayList<Employee> empregados = new ArrayList<Employee>();
    private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    private ArrayList<Room> quartos = new ArrayList<Room>();

    public Hotel() {
        id += count;
        count++;
    }

    public int getID() {
        return id;
    }

    public void addEmployee() {
        int start;
        do {
            System.out.println(
                    linha + "\nBem Vindo! Você está entrando ao Hall de comando do Hotel " + getID() + "\n" + linha);
            System.out.println("Escolha as opções de comando a seguir!\n" + linha);
            System.out.println("Selecione uma opção:");
            System.out.println("1. Criar funcionário novo");
            System.out.println("2. Comandar funções de Funcionários");
            System.out.println("3. Ver os funcionários do sistema");
            System.out.println("4. Registrar um novo quartos");
            System.out.println("5. Ver quartos");
            System.out.println("6. Registrar um novo cliente em alguns dos quartos");
            System.out.println("7. Sair\n" + linha);
            start = scanner.nextInt();
            switch (start) {
                case 1:
                    System.out.println("Escolha uma área:");
                    System.out.println("1. Administração\n2. Recepção\n3. Cozinha\n4. Limpeza");
                    int choice = scanner.nextInt();
                    System.out.println("Qual o nome do funcionário?");
                    String name = scanner.next();
                    System.out.println("Qual a idade do funcionário?");
                    int age = scanner.nextInt();
                    System.out.println("Qual o sexo do funcionário?");
                    String sex = scanner.next();
                    System.out.println("Quanto esse funcionário vai ganhar?");
                    Double wage = scanner.nextDouble();
                    switch (choice) {
                        case 1:
                            empregados.add(empregados.size(), new AdmnistrationStaff(name, sex, age, wage));
                            break;
                        case 2:
                            empregados.add(empregados.size(), new ReceptionStaff(name, sex, age, wage));
                            break;
                        case 3:
                            empregados.add(empregados.size(), new KitchenStaff(name, sex, age, wage));
                            break;
                        case 4:
                            empregados.add(empregados.size(), new CleaningStaff(name, sex, age, wage));
                            break;
                        default:
                            System.out.println("Opção inválida.\n" + linha);
                    }
                    break;
                case 2:
                    System.out.println("Digite o ID do funcionário:");
                    int id = scanner.nextInt();
                    for (int i = 0; i < empregados.size(); i++) {
                        if (empregados.get(i).getID() == id) {
                            switch (empregados.get(i).getRole()) {
                                case "Admnistração":
                                    AdmnistrationStaff a = (AdmnistrationStaff) empregados.get(i);
                                    // a.payEmployee(a);
                                    System.out.println(a.getName());
                                    break;
                                case "Recepção":
                                    ReceptionStaff r = (ReceptionStaff) empregados.get(i);
                                    // r.showRoom(id);
                                    break;
                                case "Cozinha":
                                    KitchenStaff k = (KitchenStaff) empregados.get(i);
                                    // k.cook();
                                    break;
                                case "Limpeza":
                                    CleaningStaff c = (CleaningStaff) empregados.get(i);
                                    // c.clearRoom(id);
                                    break;
                                default:
                                    System.out.println("Opção inválida.\n" + linha);
                            }
                        }
                    }
                    // System.out.println("Pressione Enter para continuar...");
                    // System.console().readLine();
                    break;
                case 3:
                    for (int i = 0; i < empregados.size(); i++){
                        System.out.println(linha + "\nID: " + empregados.get(i).getID() + "\n" + empregados.get(i));
                    }
                    System.out.println("Pressione Enter para continuar...");
                    System.console().readLine();
                    break;
                case 4:
                    Room newRoom = new Room();
                    quartos.add(quartos.size(), newRoom);
                    System.out.println(linha + "Sala " + newRoom.getID() + " adicionada!");
                    break;
                case 5:
                    break;
                case 6:
                    addCliente();
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Opção inválida.\n" + linha);
                    break;
            }
        } while (start != 7);
    }

    public void addCliente() {
        if (verifyOpen()) {
            if (verifyRooms()){
                clientes.add(clientes.size(), new Cliente("test", "M", 1, "Pro"));
            }
            else{
                System.out.println("O Hotel não possúi quartos disponíveis.");
            }
        } else {
            System.out.println("O hotel não está funcionando.\nFuncionários insuficientes.");
        }
    }

    public Boolean verifyOpen(){
        boolean[] n = new boolean[4];
        for (int i = 0; i < empregados.size(); i++){
            if(empregados.get(i).getRole() == "Admnistração"){
                n[0] = true;
                break;
            }
        }
        for (int i = 0; i < empregados.size(); i++){
            if(empregados.get(i).getRole() == "Recepção"){
                n[1] = true;
                break;
            }
        }
        for (int i = 0; i < empregados.size(); i++){
            if(empregados.get(i).getRole() == "Cozinha"){
                n[2] = true;
                break;
            }
        }
        for (int i = 0; i < empregados.size(); i++){
            if(empregados.get(i).getRole() == "Limpeza"){
                n[3] = true;
                break;
            }
        }
        for (int i = 0; i < n.length; i++){
            if (n[i] == false){
                System.out.println(n[i]);
            }
        }
        return true;
    }

    public Boolean verifyRooms(){
        if (quartos.size() > 0){
            for (int i = 0; i < quartos.size(); i++){
                if (quartos.get(i).getClientes() < 4){
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
