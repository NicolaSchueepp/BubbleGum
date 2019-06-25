package ch.bbcag.bubblegum.dao.util;

@FunctionalInterface
public interface ExecutionUnit<I,O> {

	public O execute(I input);
}
