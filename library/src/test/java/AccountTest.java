import com.thoughtworks.step.bank.Account;
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
        account = new Account("1234-1234", 1000.00);
    }

    @Test
    public void checkBalance() {
        assertThat(account.getBalance(), is(1000.00));
    }

    @Test(expected = InvalidAccountNumber.class)
    public void validateAccountNumber() throws MinimumBalanceException, InvalidAccountNumber {
        new Account("1234-12", 2000.00);
    }

    @Test
    public void checkAccountNumber() {
        assertThat(account.getAccountNumber(),is("1234-1234"));
    }

    @Test(expected = MinimumBalanceException.class)
    public void checkMinimumBalance() throws MinimumBalanceException, InvalidAccountNumber {
        new Account("2345-2345", 200);
    }

    @Test
    public void withdrawValidAmount() throws MinimumBalanceException, InvalidAccountNumber {
        Account account = new Account("1234-1234", 4000.00);
        account.debit(2000);
        assertThat(account.getBalance(),is(2000.00));
    }

    @Test(expected = MinimumBalanceException.class)
    public void withdrawInvalidAmount() throws MinimumBalanceException, InvalidAccountNumber {
        Account account = new Account("1234-2345", 3000.00);
        account.debit(2000);
        assertThat(account.getBalance(),is(2000.00));
    }
}