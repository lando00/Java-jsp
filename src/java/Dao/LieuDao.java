package Dao;

import java.util.List;
import Bean.Lieu;

public interface LieuDao {
    boolean create(Lieu S);
    boolean update(Lieu S);
    boolean delete(long id);
    Lieu recuper(long id);
    List<Lieu> recuper_all();
}