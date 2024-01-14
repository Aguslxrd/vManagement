package org.aguslxrd.Modules;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BackupModule {
    private Path sourceDirectory;
    private Path backupDirectory;
    private ScheduledExecutorService scheduler;

    public BackupModule(String sourceDir, String backupDir) {
        this.sourceDirectory = Paths.get(sourceDir);
        this.backupDirectory = Paths.get(backupDir);
        this.scheduler = Executors.newScheduledThreadPool(1);
    }

    public void startBackupScheduler() {
        scheduler.scheduleAtFixedRate(() -> {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
                String backupDirectoryName = "backup" + dateFormat.format(new Date());
                Path newBackupDirectory = backupDirectory.resolve(backupDirectoryName);

                copyDirectory(sourceDirectory, newBackupDirectory);

                System.out.println("Backup realizado en: " + newBackupDirectory.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 0, 12, TimeUnit.HOURS);
    }

    private static void copyDirectory(Path source, Path target) throws IOException {
        Files.walkFileTree(source, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
                Path targetFile = target.resolve(source.relativize(file));
                Files.copy(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attributes) throws IOException {
                Path newDirectory = target.resolve(source.relativize(dir));
                Files.createDirectories(newDirectory);
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
