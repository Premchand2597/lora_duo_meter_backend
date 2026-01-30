package com.loraDuoMeter.Repo;

public interface IPacketDecoder<T> {

	T decode(String hex);
}
