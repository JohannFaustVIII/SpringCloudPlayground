package joh.faust.model;

import java.util.UUID;

public record Post(UUID id, String name, String content, UUID user) {

}
