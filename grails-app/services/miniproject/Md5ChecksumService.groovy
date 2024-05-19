package miniproject

import grails.gorm.transactions.Transactional

import java.security.MessageDigest

@Transactional
class Md5ChecksumService {
    String calculateChecksum(String input) {
        def file = input.startsWith("/") ? new File(input) : new URL(input)
        def bytes = file instanceof File ? file.bytes : file.openStream().getBytes()
        def digest = MessageDigest.getInstance("MD5").digest(bytes)
        return new BigInteger(1, digest).toString(16)
    }

    boolean compareChecksums(String checksum1, String checksum2) {
        return checksum1 == checksum2
    }
}