/**
 * Created by Adrian on 30.03.2016.
 */
public class _65_Dont_ignore_exceptions {
}
/*

An empty catch block defeats the purpose of exceptions which is to force
you to handle exceptional conditions.

At the very least, the catch block should contain a comment explaining why it is appropriate
to ignore the exception.

The advice in this item applies equally to checked and unchecked exceptions.

An example of the sort of situation where it might be appropriate to ignore an
exception is when closing a FileInputStream. You haven’t changed the state of
the file, so there’s no need to perform any recovery action, and you’ve already
read the information that you need from the file, so there’s no reason to abort the
operation in progress. Even in this case, it is wise to log the exception, so that you
can investigate the matter if these exceptions happen often.


















 */