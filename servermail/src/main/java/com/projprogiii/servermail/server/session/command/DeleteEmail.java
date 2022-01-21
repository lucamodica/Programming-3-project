package com.projprogiii.servermail.server.session.command;

import com.projprogiii.lib.enums.ServerResponseName;
import com.projprogiii.lib.objects.ClientRequest;
import com.projprogiii.lib.objects.Email;
import com.projprogiii.lib.objects.ServerResponse;
import com.projprogiii.servermail.ServerApp;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DeleteEmail extends Command {

    @Override
    public ServerResponse handle(ClientRequest req) {
        Email email = (Email) req.arg();
        ServerResponseName name;
        ReentrantReadWriteLock.WriteLock writeLock =
                syncManager.getLock(req.auth()).writeLock();

        if (email == null){
            name = ServerResponseName.ILLEGAL_PARAMS;
        }
        else {
            writeLock.lock();
            name = (ServerApp.model.getDbManager()
                    .deleteEmail(email, req.auth())) ?
                    ServerResponseName.SUCCESS :
                    ServerResponseName.OP_ERROR;
            writeLock.unlock();
        }

        return new ServerResponse(name, null);
    }
}
