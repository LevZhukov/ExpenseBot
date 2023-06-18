package org.example.service.callbackCreator;

import org.springframework.stereotype.Service;

@Service
public abstract class AbstractCallback {

    protected String callbackText;
    protected Integer expenseId;
    protected Boolean toRemove;

    @Override
    public String toString() {
        return callbackText + "#" + expenseId.toString() + "#" + toRemove.toString();
    }
    public String getCallbackText() {
        return callbackText;
    }

    public void setCallbackText(String callbackText) {
        this.callbackText = callbackText;
    }

    public Integer getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Integer expenseId) {
        this.expenseId = expenseId;
    }

    public Boolean getToRemove() {
        return toRemove;
    }

    public void setToRemove(Boolean toRemove) {
        this.toRemove = toRemove;
    }
}
