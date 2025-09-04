package Service;

import Model.Account;
import DAO.AccountDAO;

public class AccountService {
    private AccountDAO accountDAO;
    
    public AccountService() {
        this.accountDAO = new AccountDAO();
    }
    
    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account registerAccount(Account account) {
        //Checking if username is blank
        if (account.getUsername() == null || account.getUsername().trim().isEmpty()) {
            return null;
        }
        //Checking if password is at least 4 char
        if (account.getPassword() == null || account.getPassword().length() < 4) {
            return null;
        }

        if (accountDAO.getAccountByUsername(account.getUsername()) != null) {
            return null;
        }

        return accountDAO.insertAccount(account);
    }

    public Account loginAccount(Account account) {
        return accountDAO.getAccountByUsernameAndPassword(account.getUsername(), account.getPassword());
    }
}
