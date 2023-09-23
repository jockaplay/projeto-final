package data;

import java.util.ArrayList;
import java.util.Scanner;
import data.classes.Employee;
import data.classes.KitchenStaff;
import data.classes.ReceptionStaff;
import data.classes.Room;
import data.classes.AdministrationStaff;
import data.classes.CleaningStaff;
import data.classes.Cliente;

public class Hotel {
    private final Scanner scanner = new Scanner(System.in);
    private static int count = 0;
    private int id = 1;

    // #LISTAS01
    private final ArrayList<Employee> empregados = new ArrayList<>();
    private final ArrayList<Cliente> clientes = new ArrayList<>();
    private final ArrayList<Room> quartos = new ArrayList<>();
    // #LISTAS01

    public Hotel() {
        id += count;
        count++;
    }

    public int getID() {
        return id;
    }

    public void actions() {
        int start;
        do {
            String linha = "========================================================================";
            System.out.println(linha + "\nBem Vindo! Você está entrando ao Hall de comando do Hotel " + getID() + "\n" + linha);
            System.out.println("Escolha as opções de comando a seguir!\n" + linha);
            System.out.println("Selecione uma opção:");
            System.out.println("1. Criar funcionário novo");
            System.out.println("2. Comandar funções de Funcionários");
            System.out.println("3. Ver os funcionários do sistema");
            System.out.println("4. Registrar um novo quartos");
            System.out.println("5. Sair\n" + linha);
            // TODO: Precisamos fazer um CRUD para cada lista pesquise #LISTAS01, não precisa ter o update. (CRD)
            start = scanner.nextInt();
            switch (start) {
                case 1:
                    System.out.println(linha + "\nEscolha uma área:");
                    System.out.println("1. Administração\n2. Recepção\n3. Cozinha\n4. Limpeza");
                    int choice = scanner.nextInt();
                    System.out.print(linha + "\nQual o nome do funcionário: ");
                    String name = scanner.next();
                    System.out.print(linha + "\nQual a idade do funcionário: ");
                    int age = scanner.nextInt();
                    System.out.print(linha + "\nQual o sexo do funcionário: ");
                    String sex = scanner.next();
                    System.out.print(linha + "\nQuanto esse funcionário vai ganhar: ");
                    double wage = scanner.nextDouble();
                    switch (choice) {
                        case 1:
                            empregados.add(empregados.size(), new AdministrationStaff(name, sex, age, wage));
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
                            break;
                    }
                    break;
                case 2:
                    System.out.print(linha + "\nDigite o ID do funcionário: ");
                    int id = scanner.nextInt();
                    for (Employee empregado : empregados) {
                        if (empregado.getID() == id) {
                            switch (empregado.getRole()) {
                                case "Administração":
                                    AdministrationStaff a = (AdministrationStaff) empregado;
                                    System.out.print(linha + "\nDigite o ID do funcionário que deseja pagar: ");
                                    int empregadoID = scanner.nextInt();
                                    System.out.println("Deseja dar um aumento? S/N");
                                    String escolha;
                                    escolha = scanner.next();
                                    if (escolha.equalsIgnoreCase("s")) {
                                        System.out.print(linha + "\nDigite o valor do aumento: ");
                                        double aumento = scanner.nextDouble();
                                        System.out.println(linha);
                                        a.payEmployee(empregados.get(empregadoID - 1), aumento);
                                    } else if (escolha.equalsIgnoreCase("n")) {
                                        a.payEmployee(empregados.get(empregadoID));
                                    }
                                    break;
                                case "Recepção":
                                    ReceptionStaff r = (ReceptionStaff) empregado;
                                    System.out.println(linha + "Olá, me chamo " + r.getName() + " em que posso ajudar?\n1. Cadastrar cliente\n2. Mostrar quartos disponíveis");
                                    int recepChoice = scanner.nextInt();
                                    switch (recepChoice){
                                        case 1:
                                            r.addCliente(empregados, quartos, clientes, linha, scanner);
                                            break;
                                        case 2:
                                            r.showRoom(quartos, linha);
                                            break;
                                        default:
                                            break;
                                    }
                                    break;
                                case "Cozinha":
                                    // TODO: Precisamos fazer com que as funções de cada classe funcione, nem que seja um print.
                                    // Falta a parte da cozinha.
                                    KitchenStaff k = (KitchenStaff) empregado;
                                    k.cook();
                                    break;
                                case "Limpeza":
                                    CleaningStaff c = (CleaningStaff) empregado;
                                    System.out.println("Deseja limpar os quartos vazios? S/N");
                                    String limpar_quartos = scanner.next();
                                    if (limpar_quartos.equalsIgnoreCase("s")){
                                        c.clearRoom();
                                    } else if (limpar_quartos.equalsIgnoreCase("n")){
                                        System.out.println("Os quartos continuarão fechados até a hora da limpeza.");
                                    }
                                    // ainda em teste para limpeza de quarto específico
                                    break;
                                default:
                                    System.out.println("Opção inválida.\n" + linha);
                            }
                        }
                    }
                    break;
                case 3:
                    for (Employee empregado : empregados) {
                        System.out.println(linha + "\nID: " + empregado.getID() + "\n" + empregado);
                    }
                    break;
                case 4:
                    Room newRoom = new Room();
                    quartos.add(quartos.size(), newRoom);
                    System.out.println(linha + "Sala " + newRoom.getID() + " adicionada!");
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Opção inválida.\n" + linha);
                    break;
            }
        } while (start != 7);
    }
}
