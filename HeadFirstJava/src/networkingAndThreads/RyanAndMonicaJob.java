package networkingAndThreads;

public class RyanAndMonicaJob implements Runnable{
	private BankAccount account=new BankAccount();
	
	public static void main(String[] args) {
		RyanAndMonicaJob rm=new RyanAndMonicaJob();
		Thread one=new Thread(rm);
		Thread two=new Thread(rm);
		
		one.setName("Ryan");
		two.setName("Monica");
		
		one.start();
		two.start();
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			makeWithdrawal(10);
			if (account.getBalance() < 0) {
				System.out.println("Overdrawn!");
			}
		}
		System.out.println(account.getBalance());
	}
	
	synchronized void makeWithdrawal(int amount) {
		System.out.println(Thread.currentThread().getName()+" is going to withdraw");
		if(account.getBalance() >= amount){
			System.out.println(Thread.currentThread().getName()+" is going to sleep");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+" woke up");
			account.withdraw(amount);
			System.out.println(Thread.currentThread().getName()+" completes the transaction");
		}
		else{
			System.out.println("Sorry not enough for"+Thread.currentThread().getName());
		}
	}

}

class BankAccount{
	private int balance=100;
	
	int getBalance(){
		return balance;
	}
	
	void withdraw(int amount){
		balance = balance - amount;
	}
}