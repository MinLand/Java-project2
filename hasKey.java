import java.io.Serializable;

public abstract class hasKey implements Serializable
{
    protected int key;

    public hasKey(int k)
    {
        key = k;
    }

    public int getKey()
    {
        return key;
    }
}


