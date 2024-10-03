import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class calculadoraDeArea {
    private int numLados;
    private double tamanhoLado;
    private String tipoPoligono;

    public calculadoraDeArea(int numLados, double tamanhoLado) {
        this.numLados = numLados;
        this.tamanhoLado = tamanhoLado;

        if (numLados == 3) {
            this.tipoPoligono = "Triângulo";
        } else if (numLados == 4) {
            this.tipoPoligono = "Quadrado";
        } else {
            this.tipoPoligono = "Polígono não suportado";
        }
    }

    public String getTipoPoligono() {
        return tipoPoligono;
    }

    public double calcularArea() {
        if (numLados == 3) {
            // Fórmula da área do triângulo equilátero: (lado² * √3) / 4
            return (Math.pow(tamanhoLado, 2) * Math.sqrt(3)) / 4;
        } else if (numLados == 4) {
            // Fórmula da área do quadrado: lado²
            return Math.pow(tamanhoLado, 2);
        } else {
            return 0; // Não aplicável para outros polígonos
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<calculadoraDeArea> poligonos = new ArrayList<>();
        boolean continuar = true;

        while (continuar) {
            try {
                System.out.print("Informe o número de lados do polígono (3 para triângulo, 4 para quadrado): ");
                int numLados = scanner.nextInt();

                if (numLados != 3 && numLados != 4) {
                    throw new IllegalArgumentException("Somente triângulos (3 lados) e quadrados (4 lados) são suportados.");
                }

                System.out.print("Informe o tamanho do lado (em cm): ");
                double tamanhoLado = scanner.nextDouble();

                if (tamanhoLado <= 0) {
                    throw new IllegalArgumentException("O tamanho do lado deve ser maior que zero.");
                }

                calculadoraDeArea poligono = new calculadoraDeArea(numLados, tamanhoLado);
                poligonos.add(poligono);
                System.out.println(poligono.getTipoPoligono() + " adicionado com sucesso!");

                System.out.print("Deseja adicionar outro polígono? (s/n): ");
                String resposta = scanner.next();
                continuar = resposta.equalsIgnoreCase("s");

            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, insira um número válido.");
                scanner.next(); // Limpa a entrada inválida
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        // Calcular e exibir áreas quando solicitado
        System.out.print("Deseja calcular as áreas dos polígonos inseridos? (s/n): ");
        String respostaCalculo = scanner.next();

        if (respostaCalculo.equalsIgnoreCase("s")) {
            double areaTotal = 0;
            System.out.println("\nÁreas dos polígonos inseridos:");

            for (int i = 0; i < poligonos.size(); i++) {
                calculadoraDeArea p = poligonos.get(i);
                double area = p.calcularArea();
                System.out.printf("%d. %s - Área: %.2f cm²\n", i + 1, p.getTipoPoligono(), area);
                areaTotal += area;
            }

            System.out.printf("\nÁrea total dos polígonos: %.2f cm²\n", areaTotal);
        }

        scanner.close();
    }
}
