package joh.faust.model;

import java.util.List;
import java.util.UUID;

public record User(UUID id, String name, List<UUID> posts) {

}
