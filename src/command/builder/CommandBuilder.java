package command.builder;

import command.ServiceBlockAccountPage;
import command.ServiceChangeLanguagePage;
import command.ServiceCreateAccountPage;
import command.ServiceFillAccountPage;
import command.ServiceLogoutPage;
import command.ServiceSignUpPage;
import command.ServiceTransferPage;
import command.ServiceCloseAccountPage;
import command.ServiceUnlockAccountPage;
import command.ServiseGetInfoAboutClientPage;
import command.ServiceLoginPage;
import command.ServicePageCommand;
import constants.CustomConstants;

public class CommandBuilder {
	
	public ServicePageCommand getCommand(String commandType){
		switch(commandType){
		case CustomConstants.LOG_IN_COMMAND:
			return new ServiceLoginPage();
		case CustomConstants.GET_INFO_ABOUT_CLIENT_COMMAND:
			return new ServiseGetInfoAboutClientPage();
		case CustomConstants.CLIENT_TRANSFER_COMMAND:
			return new ServiceTransferPage();
		case CustomConstants.CLOSE_ACCOUNT_CLIENT_COMMAND:
			return new ServiceCloseAccountPage();
		case CustomConstants.BLOCK_ACCOUNT_CLIENT_COMMAND:
			return new ServiceBlockAccountPage();
		case CustomConstants.FILL_BALANCE_COMMAND:
			return new ServiceFillAccountPage();
		case CustomConstants.UNLOCK_ACCOUNT_COMMAND:
			return new ServiceUnlockAccountPage();
		case CustomConstants.CREATE_ACCOUNT_COMMAND:
			return new ServiceCreateAccountPage();
		case CustomConstants.SIGN_UP_COMMAND:
			return new ServiceSignUpPage();
		case CustomConstants.LOG_OUT_COMMAND:
			return new ServiceLogoutPage();
		case CustomConstants.CHANGE_LANGUAGE_COMMAND:
			return new ServiceChangeLanguagePage();
		default:
			return null;
		}
	}

}
