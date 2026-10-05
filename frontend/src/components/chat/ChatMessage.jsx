import React from 'react';

export default function ChatMessage({ message }) {
  const isUser = message.role === 'user';

  // Simple Markdown formatter for clean structured response rendering
  const renderFormattedContent = (content) => {
    if (!content) return null;
    const lines = content.split('\n');

    return lines.map((line, idx) => {
      let trimmed = line.trim();

      if (!trimmed) return <div key={idx} style={{ height: '0.4rem' }}></div>;

      // Header level 3
      if (trimmed.startsWith('### ')) {
        return (
          <h4 key={idx} className="msg-h3">
            {trimmed.replace('### ', '')}
          </h4>
        );
      }

      // List item
      if (trimmed.startsWith('- ') || trimmed.startsWith('* ')) {
        const text = trimmed.substring(2);
        return (
          <li key={idx} className="msg-list-item">
            {parseInlineStyles(text)}
          </li>
        );
      }

      // Numbered list item
      if (/^\d+\.\s/.test(trimmed)) {
        return (
          <p key={idx} className="msg-num-item">
            {parseInlineStyles(trimmed)}
          </p>
        );
      }

      return (
        <p key={idx} className="msg-p">
          {parseInlineStyles(trimmed)}
        </p>
      );
    });
  };

  const parseInlineStyles = (text) => {
    // Bold formatting **text**
    const parts = text.split(/(\*\*.*?\*\*)/g);
    return parts.map((part, i) => {
      if (part.startsWith('**') && part.endsWith('**')) {
        return <strong key={i}>{part.slice(2, -2)}</strong>;
      }
      return part;
    });
  };

  return (
    <div className={`chat-message ${isUser ? 'user' : 'assistant'}`}>
      <div className={`avatar ${isUser ? 'user-avatar' : 'assistant-avatar'}`}>
        {isUser ? '👤' : '🤖'}
      </div>

      <div className={`message-bubble ${isUser ? 'user-bubble' : 'glass-card assistant-bubble'}`}>
        <div className="message-content">
          {isUser ? <p>{message.content}</p> : renderFormattedContent(message.content)}

          {!isUser && message.knowledgeSources && message.knowledgeSources.length > 0 && (
            <div className="knowledge-sources-box" style={{ marginTop: '0.85rem', paddingTop: '0.65rem', borderTop: '1px solid var(--border-color)', fontSize: '0.82rem' }}>
              <div style={{ fontWeight: 600, color: 'var(--accent-cyan)', marginBottom: '0.35rem', display: 'flex', alignItems: 'center', gap: '0.4rem' }}>
                <span>📚 Cited Knowledge & Scheme Sources:</span>
              </div>
              <ul style={{ margin: 0, paddingLeft: '1.2rem', color: 'var(--text-muted)' }}>
                {message.knowledgeSources.map((src, i) => (
                  <li key={i} style={{ marginBottom: '0.2rem' }}>
                    <strong>{src.title}</strong> ({src.category}) — <em style={{ fontSize: '0.78rem' }}>{src.citation}</em>
                  </li>
                ))}
              </ul>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}
