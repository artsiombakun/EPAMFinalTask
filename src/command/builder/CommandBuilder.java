package command.builder;
/**
 * @author Artyom
 * */
import util.Config;
import command.BlockAccounCommand;
import command.CreateAccountCommand;
import command.FillAccountCommand;
import command.LogoutCommand;
import command.SignUpCommand;
import command.TransferCommand;
import command.CloseAccountCommand;
import command.UnlockAccountCommand;
import command.GetInfoAboutClientCommand;
import command.LoginCommand;
import command.Command;

public class CommandBuilder {
	/**
	 * Provide new command depend on parameter
	 * @param commandType - type of new command
	 * @return new command with Command interface 
	 * (null if unknown type at parameter)
	 * @see Command
	 * */
	public Command getCommand(String commandType){
		switch(commandType){
		case Config.LOG_IN_COMMAND:
			return new LoginCommand();
		case Config.GET_INFO_ABOUT_CLIENT_COMMAND:
			return new GetInfoAboutClientCommand();
		case Config.CLIENT_TRANSFER_COMMAND:
			return new TransferCommand();
		case Config.CLOSE_ACCOUNT_CLIENT_COMMAND:
			return new CloseAccountCommand();
		case Config.BLOCK_ACCOUNT_CLIENT_COMMAND:
			return new BlockAccounCommand();
		case Config.FILL_BALANCE_COMMAND:
			return new FillAccountCommand();
		case Config.UNLOCK_ACCOUNT_COMMAND:
			return new UnlockAccountCommand();
		case Config.CREATE_ACCOUNT_COMMAND:
			return new CreateAccountCommand();
		case Config.SIGN_UP_COMMAND:
			return new SignUpCommand();
		case Config.LOG_OUT_COMMAND:
			return new LogoutCommand();
		default:
			return null;
		}
	}

}
