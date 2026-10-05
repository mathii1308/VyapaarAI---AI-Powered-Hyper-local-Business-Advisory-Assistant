import React, { useState } from 'react';

export default function ChatInput({ onSendMessage, disabled }) {
  const [text, setText] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!text.trim() || disabled) return;
    onSendMessage(text.trim());
    setText('');
  };

  const handleKeyDown = (e) => {
    if (e.key === 'Enter' && !e.shiftKey) {
      e.preventDefault();
      handleSubmit(e);
    }
  };

  return (
    <form onSubmit={handleSubmit} className="chat-input-form">
      <input
        type="text"
        className="chat-input-field"
        value={text}
        onChange={(e) => setText(e.target.value)}
        onKeyDown={handleKeyDown}
        placeholder="Ask VyapaarAI about your business, cash flows, expansion, or loans..."
        disabled={disabled}
      />
      <button type="submit" className="btn-primary chat-send-btn" disabled={disabled || !text.trim()}>
        <span>Send</span>
        <span>➔</span>
      </button>
    </form>
  );
}
