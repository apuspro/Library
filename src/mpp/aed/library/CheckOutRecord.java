package mpp.aed.library;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckOutRecord implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -4194472965517349967L;
	private final List<Entry> entries = new ArrayList<>();
    private final List<Fine> fines = new ArrayList<>();

    public List<Entry> getEntries() {
        return entries;
    }

    public List<Fine> getFines() {
        return fines;
    }

    public boolean addEntry(Entry entry) {
        return entries.add(entry);
    }
    
    public boolean addFine(Fine fine) {
        return fines.add(fine);
    }  
}
