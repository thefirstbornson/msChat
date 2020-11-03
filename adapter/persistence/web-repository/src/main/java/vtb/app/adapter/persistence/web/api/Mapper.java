package vtb.app.adapter.persistence.web.api;

public interface Mapper<D,S> {
    D mapFrom(S source);
}
