
/**
 * Write a description of class LockDListNode here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LockDListNode extends DListNode
{
    protected boolean locked;
    public LockDListNode(Object item, DListNode prev, DListNode next) {
        super(item, prev, next);
        locked = false;
    }
}
