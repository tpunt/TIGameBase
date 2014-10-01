package net.rphp.TIGameBase;

/**
 * This class acts as the communication envelope containing the details of the
 * action executed by the player. It is passed from the Parser class to the
 * GameBase class, where its contents is read.
 */
public class ResultantAction
{
    private String commandWord;
    private boolean errorOccurred = false;
    private String responseMessage;

    /**
     * Set the command word used, the response message from executing the
     * corresponding command word action, and set whether an error has occurred.
     * <p>
     * This constructor is generally used if an error has occurred (setting the third
     * argument to 'true' on class instantiation).
     */
    public ResultantAction(String commandWord, String responseMessage, boolean errorOccurred)
    {
        this.commandWord = commandWord;
        this.responseMessage = responseMessage;
        this.errorOccurred = errorOccurred;
    }

    /**
     * Set the command word used and the response message from executing the
     * corresponding command word action.
     */
    public ResultantAction(String commandWord, String responseMessage)
    {
        this.commandWord = commandWord;
        this.responseMessage = responseMessage;
    }

    /**
     * Get the command word used in the command line entered.
     *
     * @return 	the command word used
     */
    public String getCommandWord()
    {
        return commandWord;
    }

    /**
     * Check whether an error has occurred when parsing the input command.
     *
     * @return 	whether the object claims an error as occurred
     */
    public boolean hasError()
    {
        return errorOccurred;
    }

    /**
     * Get the the response when parsing the command line entered.
     *
     * @return 	the response of the action executed
     */
    public String getResponse()
    {
        return responseMessage;
    }
}