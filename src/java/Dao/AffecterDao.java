package Dao;

import Bean.Affecter;
import java.util.List;

public interface AffecterDao {
    boolean create(Affecter o);
    boolean update(Affecter o);
    boolean delete(long id);
    Affecter recuper(long id);
    List<Affecter> recuper_all();
}