import com.thoughtworks.step.bank.Account;
import com.thoughtworks.step.bank.LowAccountBalanceException;
import com.thoughtworks.step.bank.MinimumBalanceException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountTest {

    private Account account;

    @Before
    public void setUp() throws Exception, MinimumBalanceException {
        account = new Account("1234", 1000.00);
    }

    @Test
    public void checkBalance() {
        assertThat(account.getBalance(), is(1000.00));
    }

    @Test
    public void checkAccountNumber() {
        assertThat(account.getAccountNumber(),is("1234"));
    }

    @Test(expected = MinimumBalanceException.class)
    public void checkMinimumBalance() throws MinimumBalanceException {
        new Account("2345", 200);
    }

    @Test
    public void withdrawValidAmount() throws LowAccountBalanceException, MinimumBalanceException {
        Account account = new Account("1234", 4000.00);
        account.debit(2000);
        assertThat(account.getBalance(),is(2000.00));
    }

    @Test(expected = LowAccountBalanceException.class)
    public void withdrawInvalidAmount() throws MinimumBalanceException, LowAccountBalanceException {
        Account account = new Account("1234", 3000.00);
        account.debit(2000);
        assertThat(account.getBalance(),is(2000.00));
    }
}