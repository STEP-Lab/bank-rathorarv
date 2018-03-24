import com.thoughtworks.step.bank.Account;
import com.thoughtworks.step.bank.AccountNumber;
import com.thoughtworks.step.bank.InvalidAccountNumber;
import com.thoughtworks.step.bank.MinimumBalanceException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountTest {

    private Account account;

    @Before
    public void setUp() throws MinimumBalanceException, InvalidAccountNumber {
        account = new Account(new AccountNumber("1234-1234"), 2000.00);
    }

    @Test
    public void checkBalance() {
        assertThat(account.getBalance(), is(2000.00));
    }

    @Test(expected = InvalidAccountNumber.class)
    public void validateAccountNumber() throws MinimumBalanceException, InvalidAccountNumber {
        new Account(new AccountNumber("1234-123a"), 2000.00);
    }

    @Test(expected = MinimumBalanceException.class)
    public void checkMinimumBalance() throws MinimumBalanceException, InvalidAccountNumber {
        new Account(new AccountNumber("1234-1234"), 200.00);
    }

    @Test
    public void withdrawValidAmount() throws MinimumBalanceException, InvalidAccountNumber {
        Account account = new Account(new AccountNumber("1234-1234"), 2000.00);
        account.debit(800);
        assertThat(account.getBalance(),is(1200.00));
    }

    @Test(expected = MinimumBalanceException.class)
    public void withdrawInvalidAmount() throws MinimumBalanceException, InvalidAccountNumber {
        Account account = new Account(new AccountNumber("1234-1234"), 2000.00);
        account.debit(2000);
        assertThat(account.getBalance(),is(2000.00));
    }
    @Test
    public void credit() throws MinimumBalanceException, InvalidAccountNumber {
        Account account = new Account(new AccountNumber("1234-1234"), 2000.00);
        account.credit(200.490);
        assertThat(account.getBalance(),is(2200.490));
    }
}