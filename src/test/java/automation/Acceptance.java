package automation;

import junit.framework.TestSuite;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	Register.class,
	LoginAndLogout.class,
	ChangePassword.class,
	SearchProducts.class, 
	ViewProducts.class,
	WishListProducts.class,
	AddProducts.class,
	FinishPurchase.class,//dependent on previous test
	FileReturnTicket.class,//dependent on previous test

})
public class Acceptance extends TestSuite {

	@BeforeClass
	public static void Initialization() {
		Base att = new Base();
		att.Acceptance_test();
	}
}