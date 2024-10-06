public class Main {
 public static void main(String[] args) {
     ContaBancaria conta = new ContaBancaria(500); // Saldo inicial da conta
     String arquivoOperacoes = "operacoes.txt"; // Nome do arquivo de operações

     try (BufferedReader br = new BufferedReader(new FileReader("operacoes.txt"))) {
         String linha;
         while ((linha = br.readLine()) != null) {
             String[] partes = linha.split(","); 
             String operacao = partes[0].trim();
             double valor = Double.parseDouble(partes[1].trim());

             Thread t = new Thread(new OperacaoUsuario(conta, operacao, valor));
             t.start();

             Thread.sleep(100); 
         }
     } catch (IOException | InterruptedException e) {
         e.printStackTrace();
     }

     System.out.println("Saldo final: R$" + conta.getSaldo());
 }
}
